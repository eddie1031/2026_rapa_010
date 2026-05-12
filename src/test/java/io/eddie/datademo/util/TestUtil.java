package io.eddie.datademo.util;

import io.eddie.datademo.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtil {

    private static final String ITEM_PREFIX = "ITEM_";

    public static String genRandomItemCode() {
//        return ITEM_PREFIX + UUID.randomUUID().toString().replaceAll("-", "");
        return ITEM_PREFIX + genNumStr();
    }

    public static String genNumStr() {
        int num = (int) (Math.random() * 100_000);
        return Integer.toString(num);
    }

    public static Integer genRandomPrice() {
        int price = (int) (Math.random() * 100_000);
        return price * 1_000;
    }

    public static Item generateItem() {
        return Item.builder()
                .name(genRandomItemCode())
                .code(genRandomItemCode())
                .price(genRandomPrice())
                .build();
    }

    public static List<Item> generateItemList(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(_ -> generateItem())
                .toList();
    }

}
