package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataJpaOrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.code = :orderCode")
    Optional<Order> findByOrderCode(String orderCode);

    Optional<Order> findByCode(String code);

//    void deleteByCode(String code);

}


