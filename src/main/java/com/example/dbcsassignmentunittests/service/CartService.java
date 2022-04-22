package com.example.dbcsassignmentunittests.service;

import com.example.dbcsassignmentunittests.dto.CartDto;
import com.example.dbcsassignmentunittests.model.Cart;
import com.example.dbcsassignmentunittests.model.ProductAndQuantity;
import com.example.dbcsassignmentunittests.repository.CartRepository;
import com.example.dbcsassignmentunittests.repository.ProductAndQuantityRepository;
import com.example.dbcsassignmentunittests.repository.ProductRepository;
import com.example.dbcsassignmentunittests.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductAndQuantityRepository productAndQuantityRepository;
    private final UserRepository userRepository;

    public static Cart computeTotal(Cart cart) {
        double total = cart
                .getProductAndQuantityList()
                .stream()
                .map(productAndQuantity -> productAndQuantity.getProduct().getPrice() * productAndQuantity.getQuantity())
                .reduce((double) 0, Double::sum);
        cart.setComputedTotal(total);
        return cart;
    }

    public Cart get(Long id) {
        return computeTotal(cartRepository.getById(id));
    }

    public List<Cart> getAllSortedByTotal() {
        return cartRepository
                .findAll()
                .stream()
                .map(CartService::computeTotal)
                .sorted()
                .collect(Collectors.toList());
    }

    public Cart save(Long id, String username, Map<String, Long> productIdsAndQuantities) {
        List<ProductAndQuantity> productAndQuantityList = new ArrayList<>();
        productIdsAndQuantities.forEach((productCode, quantity) -> 
            productAndQuantityList.add(productAndQuantityRepository.save(new ProductAndQuantity(
                    productRepository.getById(productCode),
                    quantity
            )))
        );
        return computeTotal(cartRepository.save(
                new Cart(
                        id,
                        userRepository.getById(username),
                        productAndQuantityList
                )
        ));
    }

    public Cart save(CartDto dto) {
        Map<String, Long> productIdsAndQuantities = new HashMap<>();
        for (CartDto.ProductCodeAndQuantity o: dto.getProducts()) {
            productIdsAndQuantities.merge(o.getCode(), o.getQuantity(), Long::sum);
        }
        return save(dto.getId(), dto.getUsername(), productIdsAndQuantities);
    }

    public Cart add(String username, Map<String, Long> productIdsAndQuantities) {
        return save(null, username, productIdsAndQuantities);
    }

    public Cart add(CartDto dto) {
        dto.setId(null);
        Map<String, Long> productIdsAndQuantities = new HashMap<>();
        for (CartDto.ProductCodeAndQuantity o: dto.getProducts()) {
            productIdsAndQuantities.merge(o.getCode(), o.getQuantity(), Long::sum);
        }
        return add(dto.getUsername(), productIdsAndQuantities);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }


}
