package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import io.eddie.datademo.domain.OrderItem;

import java.util.Optional;

public interface OrderRepository {

    Order saveOrder(Order order);
    Optional<Order> findOrderByOrderCode(String orderCode);
    String removeOrderByOrderCode(String orderCode);

    OrderItem saveOrderItems(OrderItem orderItem);

}
