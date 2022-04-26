package com.example.dbcsassignmentunittests.service;

import com.example.dbcsassignmentunittests.dto.CartDto;
import com.example.dbcsassignmentunittests.dto.WishlistDto;
import com.example.dbcsassignmentunittests.model.ProductAndQuantity;
import com.example.dbcsassignmentunittests.model.Wishlist;
import com.example.dbcsassignmentunittests.repository.ProductAndQuantityRepository;
import com.example.dbcsassignmentunittests.repository.ProductRepository;
import com.example.dbcsassignmentunittests.repository.WishlistRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductAndQuantityRepository productAndQuantityRepository;
    private final ProductRepository productRepository;

    public Wishlist save(WishlistDto dto) {
        List<ProductAndQuantity> productAndQuantityList = new ArrayList<>();
        dto.setProductCodes(
                dto.getProductCodes().stream()
                        .distinct()
                        .collect(Collectors.toList())
        );
        for(String productCode: dto.getProductCodes()) {
            ProductAndQuantity productAndQuantity = productAndQuantityRepository.save(new ProductAndQuantity(
                    productRepository.getById(productCode),
                    1L
            ));
            productAndQuantityList.add(productAndQuantity);
        }
        return wishlistRepository.save(new Wishlist(
                dto.getUsername(),
                productAndQuantityList
        ));
    }

    public List<Wishlist> getAll() {
        return wishlistRepository.findAll();
    }

    public Wishlist get(String username) {
        return wishlistRepository.getById(username);
    }

    public void delete(String username) {
        wishlistRepository.deleteById(username);
    }

}
