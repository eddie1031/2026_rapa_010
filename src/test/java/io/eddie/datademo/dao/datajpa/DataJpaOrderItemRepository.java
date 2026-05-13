package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaOrderItemRepository extends JpaRepository<OrderItems, Long> {
}
