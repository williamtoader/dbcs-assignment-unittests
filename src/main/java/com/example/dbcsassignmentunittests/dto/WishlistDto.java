package com.example.dbcsassignmentunittests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto {
    String username;
    List<String> productCodes;
}
