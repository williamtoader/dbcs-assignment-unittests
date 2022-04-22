package com.example.dbcsassignmentunittests.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    private String code;
    private String name;
    private String description;
    private Double price;
    private Long stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getCode().equals(product.getCode()) && getName().equals(product.getName()) && Objects.equals(getDescription(), product.getDescription()) && getPrice().equals(product.getPrice()) && getStock().equals(product.getStock());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), getDescription(), getPrice(), getStock());
    }
}
