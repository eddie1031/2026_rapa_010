package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataJpaOrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
    select
        oi
    from
        OrderItem oi
    where
        oi.order.code = :orderCode
    """)
    List<OrderItem> findAllByOrderCode(String orderCode);

}
