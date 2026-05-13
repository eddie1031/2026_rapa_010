package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Item;
import io.eddie.datademo.util.TestUtil;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
class DataJpaItemRepositoryTests {

    @Autowired
    DataJpaItemRepository repository;

    Item item;

    @BeforeEach
    void init() {
        item = repository.save(
                TestUtil.generateItem()
        );
    }

    @Test
    void it_will_save() {

        Item item = TestUtil.generateItem();

        repository.save(item);

    }

    @Test
    void it_will_find_one_with_id() {

        Long targetId = item.getId();

        Optional<Item> itemOptional = repository.findById(targetId);

        assertThat(itemOptional.isPresent()).isTrue();

        Item findItem = itemOptional.get();

        assertThat(findItem.getCode()).isEqualTo(item.getCode());


    }

    @Test
    void it_will_update() {

        Integer targetPrice = 30_000;

        Long targetId = item.getId();

        Optional<Item> itemOptional = repository.findById(targetId);
        assertThat(itemOptional.isPresent()).isTrue();

        Item item = itemOptional.get();
        item.setPrice(targetPrice);

        Item updatedItem = repository.findById(targetId).get();
        assertThat(updatedItem.getPrice()).isEqualTo(targetPrice);


    }

    @Test
    void it_will_delete() {

        repository.delete(item);

        Optional<Item> itemOptional = repository.findById(item.getId());
        assertThat(itemOptional.isPresent()).isFalse();

    }

}