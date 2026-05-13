package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import io.eddie.datademo.domain.OrderItems;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
        return Optional.empty();
    }

    @Override
    public String removeOrderByOrderCode(String orderCode) {
        return "";
    }

    @Override
    public OrderItems saveOrderItems(OrderItems orderItems) {
        return null;
    }

}
