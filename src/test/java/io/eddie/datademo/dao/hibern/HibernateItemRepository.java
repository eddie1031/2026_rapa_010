package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

//        entityManager.find(Item.class, id);

        Item findItem = entityManager.createQuery("select i from Item i where i.code = :code", Item.class)
                .setParameter("code", code)
                .getSingleResult();

        return findItem;

    }


}
