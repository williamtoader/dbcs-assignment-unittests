package com.example.dbcsassignmentunittests.service;

import com.example.dbcsassignmentunittests.model.Cart;
import com.example.dbcsassignmentunittests.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public static double getTotal(Cart cart) {
        return cart
                .getProductAndQuantityList()
                .stream()
                .map(productAndQuantity -> productAndQuantity.getProduct().getPrice() * productAndQuantity.getQuantity())
                .reduce((double) 0, Double::sum);
    }

    private static final class CartComparator implements Comparator<Cart>{
        @Override
        public int compare(Cart o1, Cart o2) {
            return Double.compare(getTotal(o1), getTotal(o2));
        }
    }



    public List<Cart> getAllSortedByTotal() {
        return cartRepository
                .findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
