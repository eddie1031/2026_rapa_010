package io.eddie.datademo.dao.qrydsl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.eddie.datademo.dao.datajpa.DataJpaOrderItemRepository;
import io.eddie.datademo.dao.datajpa.DataJpaOrderRepository;
import io.eddie.datademo.domain.Order;
import io.eddie.datademo.domain.OrderItem;
import io.eddie.datademo.domain.QItem;
import io.eddie.datademo.domain.QOrderItem;
import io.eddie.datademo.exceptions.CouldNotFindSuchOrderException;
import io.eddie.datademo.exceptions.CouldNotFindSuchOrderItemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static io.eddie.datademo.domain.QItem.*;
import static io.eddie.datademo.domain.QOrder.*;
import static io.eddie.datademo.domain.QOrderItem.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QueryDslOrderRepository {

    private final JPAQueryFactory queryFactory;

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemRepository orderItemRepository;

    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return queryFactory.selectFrom(order)
                .fetch();
    }

    @Transactional(readOnly = true)
    public Optional<Order> findOrderByCode(String code) {
        return Optional.ofNullable(queryFactory.selectFrom(order)
                .where(order.code.eq(code))
                .fetchOne());
    }

    @Transactional(readOnly = true)
    public Order getOrderByCode(String code) {
        return findOrderByCode(code)
                .orElseThrow(CouldNotFindSuchOrderException::new);
    }

    @Transactional
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    public void removeOrderByCode(String code) {
        Order targetOrder = getOrderByCode(code);
        orderRepository.delete(targetOrder);
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional(readOnly = true)
    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<OrderItem> findAllOrderItemsByOrderCode(String code) {
        return queryFactory.selectFrom(orderItem)
                .leftJoin(orderItem.order, order)
                .leftJoin(orderItem.item, item)
                .where(order.code.eq(code))
                .fetch();
    }

    @Transactional
    public void deleteOrderItem(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }

    @Transactional(readOnly = true)
    public Optional<OrderItem> findOrderItemById(Long id) {return Optional.ofNullable(
                queryFactory.selectFrom(orderItem)
                        .where(orderItem.id.eq(id))
                        .fetchOne()
        );
    }

    @Transactional(readOnly = true)
    public OrderItem getOrderItemById(Long id) {
        return findOrderItemById(id).orElseThrow(CouldNotFindSuchOrderItemException::new);
    }

    @Transactional
    public void updateOrderItemQtyByCode(Long orderItemId, int targetQty) {
        OrderItem findOrderItem = getOrderItemById(orderItemId);
        findOrderItem.setQuantity(targetQty);
    }

}
