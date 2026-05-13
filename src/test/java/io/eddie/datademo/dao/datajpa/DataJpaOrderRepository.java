package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaOrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCode(String code);

}


