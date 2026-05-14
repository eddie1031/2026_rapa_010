package io.eddie.datademo.dao.qrydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.eddie.datademo.domain.Item;
import io.eddie.datademo.domain.QItem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.eddie.datademo.domain.QItem.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QueryDslItemRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Transactional(readOnly = true)
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(item)
                .where(item.id.eq(id))
                .fetchFirst());
    }

}
