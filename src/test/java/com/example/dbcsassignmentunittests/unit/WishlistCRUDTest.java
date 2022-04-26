package com.example.dbcsassignmentunittests.unit;

import com.example.dbcsassignmentunittests.dto.WishlistDto;
import com.example.dbcsassignmentunittests.model.Product;
import com.example.dbcsassignmentunittests.model.User;
import com.example.dbcsassignmentunittests.service.ProductService;
import com.example.dbcsassignmentunittests.service.UserService;
import com.example.dbcsassignmentunittests.service.WishlistService;
import com.example.dbcsassignmentunittests.utils.ResourceLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class WishlistCRUDTest {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    WishlistService wishlistService;

    @Autowired
    ObjectMapper objectMapper;

    List<WishlistDto> wishlistDtoList;
    List<User> userList;
    List<Product> productList;

    private void loadData() throws Exception {
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
        wishlistDtoList = objectMapper.readValue(
                ResourceLoader.loadTestData("wishlists"),
                new TypeReference<>() {
                }
        );
    }

    @Test
    @Order(1)
    public void Given_Wishlists_When_AddingToDatabase_Then_RetrievalSuccessful() throws Exception{
        loadData();
        for (User user : userList) {
            userService.save(user);
        }
        for (Product product : productList) {
            productService.save(product);
        }
        for (WishlistDto wishlistDto: wishlistDtoList) {
            wishlistService.save(wishlistDto);
        }
        System.out.println();
    }
}
