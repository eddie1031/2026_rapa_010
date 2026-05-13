package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import io.eddie.datademo.domain.OrderItems;
import io.eddie.datademo.orders.exceptions.CouldNotFindSuchOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DataJpaOrderRepositoryCombine implements OrderRepository {

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrderByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }

    @Override
    public String removeOrderByOrderCode(String orderCode) {

        Order findOrder = orderRepository.findByCode(orderCode)
                .orElseThrow(CouldNotFindSuchOrderException::new);

        orderRepository.delete(findOrder);

        return orderCode;
    }

    @Override
    public OrderItems saveOrderItems(OrderItems orderItems) {
        return orderItemRepository.save(orderItems);
    }

}
