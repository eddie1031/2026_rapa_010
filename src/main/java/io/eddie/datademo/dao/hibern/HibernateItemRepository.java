package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public void updatePrice(String itemCode, int price) {

        Item findItem = findByCode(itemCode);
        findItem.setPrice(price);

    }

    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


    public List<Item> saveAll(List<Item> items) {

        final int BATCH_SIZE = 50;

        for ( int i = 0; i < items.size(); i++ ) {

            entityManager.persist(items.get(i));

            if ( i % BATCH_SIZE == 0 && i > 0 ) {
                entityManager.flush();
                entityManager.clear();
                log.info("FLUSH!");
            }

        }

        entityManager.flush();
        entityManager.clear();
        log.info("FLUSH!");

        return items;

    }



}
