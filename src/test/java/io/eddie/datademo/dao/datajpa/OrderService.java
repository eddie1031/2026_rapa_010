package io.eddie.datademo.dao.datajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    void save() {

//        orderRepository.saveOrder()

    }

}
