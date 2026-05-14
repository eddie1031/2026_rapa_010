package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Setter
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Item item;

    public OrderItem(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public OrderItem setOrder(Order order) {

        this.order = order;
        order.getOrderItems().add(this);

        return this;

    }


}
