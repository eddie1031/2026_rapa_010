package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HibernateItemRepositoryTests {

    @Autowired
    HibernateItemRepository repository;

    @Test
    void it_will_return_item_including_pk() {

        final String NAME = "상품1";
        final String CODE = UUID.randomUUID().toString();
        final Integer PRICE = 10_000;

        Item item = Item.builder()
                .name(NAME)
                .code(CODE)
                .price(PRICE)
                .build();

        Item saved = repository.save(item);

        Assertions.assertThat(saved.getId()).isNotNull();

    }

    @Test
    void it_will_return_available_item() {



    }


}