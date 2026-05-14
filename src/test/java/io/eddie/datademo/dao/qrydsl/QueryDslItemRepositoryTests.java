package io.eddie.datademo.dao.qrydsl;

import io.eddie.datademo.domain.Item;
import io.eddie.datademo.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static io.eddie.datademo.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class QueryDslItemRepositoryTests {

    @Autowired
    QueryDslItemRepository repository;

    @Test
    void it_will_save() {

        Item item = generateItem();

        Item saved = repository.save(item);

        Assertions.assertThat(saved.getId()).isNotNull();

    }


}