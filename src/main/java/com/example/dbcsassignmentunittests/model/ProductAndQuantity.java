package com.example.dbcsassignmentunittests.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductAndQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "cart_id")
    @ManyToOne
    private Product product;
    private Long quantity;

    public ProductAndQuantity(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
