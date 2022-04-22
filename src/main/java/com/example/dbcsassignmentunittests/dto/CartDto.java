package com.example.dbcsassignmentunittests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    @Data
    public static class ProductCodeAndQuantity {
        String code;
        Long quantity;
    }

    String username;
    List<ProductCodeAndQuantity> products;

    @JsonProperty
    Long id;
}
