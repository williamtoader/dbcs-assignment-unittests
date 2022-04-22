package com.example.dbcsassignmentunittests.unit;

import com.example.dbcsassignmentunittests.dto.CartDto;
import com.example.dbcsassignmentunittests.model.Cart;
import com.example.dbcsassignmentunittests.model.Product;
import com.example.dbcsassignmentunittests.model.User;
import com.example.dbcsassignmentunittests.service.CartService;
import com.example.dbcsassignmentunittests.service.ProductService;
import com.example.dbcsassignmentunittests.service.UserService;
import com.example.dbcsassignmentunittests.utils.ResourceLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
public class CartCRUDTest {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    List<CartDto> cartDtoList;
    List<Double> cartTotalsSorted;
    List<Product> productList;
    List<User> userList;

    public void loadData() throws Exception{
        cartDtoList = objectMapper.readValue(
                ResourceLoader.loadTestData("carts"),
                new TypeReference<>() {
                }
        );
        productList = objectMapper.readValue(
                ResourceLoader.loadTestData("products"),
                new TypeReference<>() {
                }
        );
        userList = objectMapper.readValue(
                ResourceLoader.loadTestData("users"),
                new TypeReference<>() {
                }
        );
        cartTotalsSorted = objectMapper.readValue(
                ResourceLoader.loadTestData("cart-totals-sorted"),
                new TypeReference<>() {
                }
        );
    }

    @Test
    @Order(1)
    public void Given_Carts_When_AddingToDatabase_Then_RetrievalSuccessful() throws Exception {
        loadData();
        for(User user: userList) {
            userService.save(user);
        }
        for(Product product: productList) {
            productService.save(product);
        }
        for(CartDto cartDto: cartDtoList) {
            cartService.add(cartDto);
        }
        assert cartService.getAllSortedByTotal().stream()
                .map(Cart::getComputedTotal)
                .collect(Collectors.toList())
                .equals(cartTotalsSorted);
    }
}
