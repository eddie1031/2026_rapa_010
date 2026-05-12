package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class HibernateItemRepository {

    private final EntityManager entityManager;

    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    // code
    public Item findByCode(String code) {

        try {
            return entityManager.createQuery("select i from Item i where i.code = :code", Item.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } catch ( Exception e ) {
            return null;
        }

    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(Item.class, id)
        );
    }



}
