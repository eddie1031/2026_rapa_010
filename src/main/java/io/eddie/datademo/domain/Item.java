package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Setter
    private Integer price;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Item(String name, String code, Integer price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(code, item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

}
