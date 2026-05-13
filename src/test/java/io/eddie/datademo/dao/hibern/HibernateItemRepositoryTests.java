package io.eddie.datademo.dao.hibern;

import io.eddie.datademo.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static io.eddie.datademo.util.TestUtil.generateItem;
import static io.eddie.datademo.util.TestUtil.generateItemList;
import static org.assertj.core.api.Assertions.*;

@Slf4j
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
        assertThat(saved.getId()).isNotNull();

    }

    @Test
    void it_will_return_available_item() {

        // given
        String targetCode = item.getCode();

        // when
        Item findItem = repository.findByCode(targetCode);

        // then
        assertThat(item.getId()).isEqualTo(findItem.getId());
        assertThat(item.getCode()).isEqualTo(findItem.getCode());

    }

    @Test
    void it_will_return_null() {

        // given
        final String unavailableCode = UUID.randomUUID().toString();

        // when
        Item findItem = repository.findByCode(unavailableCode);

        // then
        assertThat(findItem).isNull();

    }

    @Test
    void it_return_including_obj_optional() {

        Long targetId = item.getId();

        Optional<Item> itemOptional = repository.findById(targetId);
        assertThat(itemOptional.isPresent()).isTrue();

        Item findItem = itemOptional.get();
        assertThat(findItem).isNotNull();
        assertThat(findItem.getId()).isEqualTo(targetId);


    }

    @Test
    void it_return_empty_optional() {

        Long UNAVAILABLE_ID = 10_000L;

        Optional<Item> itemOptional = repository.findById(UNAVAILABLE_ID);
        assertThat(itemOptional.isPresent()).isFalse();

        assertThatThrownBy(
                () -> itemOptional.get()
        ).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    void it_will_change_price() {

        final int TARGET_PRICE = 10_000;
        repository.updatePrice(item.getCode(), TARGET_PRICE);

        Item findItem = repository.findByCode(item.getCode());

        assertThat(findItem.getPrice()).isEqualTo(TARGET_PRICE);

    }

    @Test
    void it_will_raise_npe() {

        final int TARGET_PRICE = 10_000;
        final String INVALID_CODE = "INVALID_CODE";

        assertThatThrownBy(() -> repository.updatePrice(INVALID_CODE, TARGET_PRICE))
                .isInstanceOf(NullPointerException.class);

    }

    @Test
    void it_return_all() {

        final int SIZE = 15;

        List<Item> items = generateItemList(SIZE);
        items.stream()
            .forEach(i -> repository.save(i));


        List<Item> findItems = repository.findAll();

        assertThat(findItems.size()).isGreaterThan(SIZE);

    }

    @Test
    void it_will_save_all() {

        int SIZE = 1000;
        List<Item> items = generateItemList(SIZE);

        List<Item> savedList = repository.saveAll(items);

        assertThat(savedList.size()).isEqualTo(SIZE);

        savedList.forEach(
                item -> assertThat(item.getId()).isNotNull()
        );

    }


}