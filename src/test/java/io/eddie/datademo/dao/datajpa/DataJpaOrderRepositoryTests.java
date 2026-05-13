package io.eddie.datademo.dao.datajpa;

import io.eddie.datademo.domain.Order;
import io.eddie.datademo.util.TestUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Transactional
class DataJpaOrderRepositoryTests {

    @Autowired
    DataJpaOrderRepository orderRepository;

    @Test
    void test1() {

        String number = TestUtil.genNumStr();

        Order orders = Order.builder()
                .code(number)
                .build();

        Order saved = orderRepository.saveAndFlush(orders);

        orderRepository.flush();

        Optional<Order> orderOptional = orderRepository.findByCode(number);
        orderOptional.get();



    }

    @Test
    void test2() {

        String number = TestUtil.genNumStr();

        Order orders = Order.builder()
                .code(number)
                .build();

        Order saved = orderRepository.saveAndFlush(orders);

        orderRepository.flush();

        Optional<Order> orderOptional = orderRepository.findByOrderCode(number);
        orderOptional.get();

    }

}