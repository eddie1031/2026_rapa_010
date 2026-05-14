package io.eddie.datademo.dao.qrydsl;

import io.eddie.datademo.domain.Item;
import io.eddie.datademo.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.eddie.datademo.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class QueryDslItemRepositoryTests {

    @Autowired
    QueryDslItemRepository repository;

    Item SAVED_ITEM;

    @BeforeEach
    void init() {
        SAVED_ITEM = repository.save(generateItem());
    }


    @Test
    void it_will_save() {

        Item item = generateItem();

        Item saved = repository.save(item);

        Assertions.assertThat(saved.getId()).isNotNull();

    }

    @Test
    void it_will_find_one() {

        Optional<Item> itemOptional = repository.findById(SAVED_ITEM.getId());
        Assertions.assertThat(itemOptional.isPresent()).isTrue();

        Item findItem = itemOptional.get();
        Assertions.assertThat(findItem.getId()).isNotNull();
        Assertions.assertThat(findItem.getId()).isEqualTo(SAVED_ITEM.getId());

    }


}