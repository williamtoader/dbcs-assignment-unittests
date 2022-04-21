package com.example.dbcsassignmentunittests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Stream;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    private String code;
    private String name;
    private String description;
    private Double price;
    private Long stock;

    //@OneToMany
    //List<ProductAndQuantity> productAndQuantityList;
}
