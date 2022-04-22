package com.example.dbcsassignmentunittests.model;

import lombok.*;
import org.hibernate.Hibernate;

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
}
