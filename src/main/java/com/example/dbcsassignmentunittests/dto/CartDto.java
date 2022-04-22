package com.example.dbcsassignmentunittests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    String username;
    List<ProductCodeAndQuantity> products;
    @JsonProperty
    Long id;

    @Data
    public static class ProductCodeAndQuantity {
        String code;
        Long quantity;
    }
}
