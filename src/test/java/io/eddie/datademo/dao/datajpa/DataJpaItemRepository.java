package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaItemRepository extends JpaRepository<Item, Long> {
}
