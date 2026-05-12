package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Item item;

    public OrderItems(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public OrderItems setOrder(Order order) {

        this.order = order;
        order.getOrderItems().add(this);

        return this;

    }


}
