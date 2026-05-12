package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import io.eddie.datademo.util.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static io.eddie.datademo.util.TestUtil.generateItem;

@SpringBootTest
class HibernateItemRepositoryTests {

    @Autowired
    HibernateItemRepository repository;

    Item item;

    @BeforeEach
    void init() {
        item = repository.save(generateItem());
    }

    @Test
    void it_will_return_item_including_pk() {

        // given
        Item item = generateItem();

        // when
        Item saved = repository.save(item);

        // then
        Assertions.assertThat(saved.getId()).isNotNull();

    }

    @Test
    void it_will_return_available_item() {

        // given
        String targetCode = item.getCode();

        // when
        Item findItem = repository.findByCode(targetCode);

        // then
        Assertions.assertThat(item.getId()).isEqualTo(findItem.getId());
        Assertions.assertThat(item.getCode()).isEqualTo(findItem.getCode());

    }

    @Test
    void it_will_return_null() {

        // given
        final String unavailableCode = UUID.randomUUID().toString();

        // when
        Item findItem = repository.findByCode(unavailableCode);

        // then
        Assertions.assertThat(findItem).isNull();

    }

    @Test
    void it_will_return_available_item_by_id() {



    }


}