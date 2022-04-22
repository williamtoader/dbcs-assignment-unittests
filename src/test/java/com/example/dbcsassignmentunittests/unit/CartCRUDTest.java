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

    public void loadData() throws Exception {
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
        for (User user : userList) {
            userService.save(user);
        }
        for (Product product : productList) {
            productService.save(product);
        }
        Long firstId = null;
        for (CartDto cartDto : cartDtoList) {
            Long id = cartService.add(cartDto).getId();
            if (firstId == null) firstId = id;
        }
        assert cartService.getAllSortedByTotal().stream()
                .map(Cart::getComputedTotal)
                .collect(Collectors.toList())
                .equals(cartTotalsSorted);

        assert cartService.get(firstId).getUser().getUsername().equals("d34th");
    }

    @Test
    @Order(2)
    public void Given_Carts_When_Updating_Then_UpdateSuccessful() throws Exception {
        loadData();
        for (User user : userList) {
            userService.save(user);
        }
        for (Product product : productList) {
            productService.save(product);
        }
        Long firstId = null;
        for (CartDto cartDto : cartDtoList) {
            Long id = cartService.add(cartDto).getId();
            if (firstId == null) firstId = id;
        }
        assert cartService.getAllSortedByTotal().stream()
                .map(Cart::getComputedTotal)
                .collect(Collectors.toList())
                .equals(cartTotalsSorted);

        assert cartService.get(firstId).getUser().getUsername().equals("d34th");

        CartDto cartDto = cartDtoList.get(0);
        cartDto.setProducts(cartDto.getProducts().subList(0, 1));
        cartDto.setId(firstId);
        cartService.save(cartDto);
        assert cartService.get(firstId).getProductAndQuantityList().size() == 1;
    }

    @Test
    @Order(3)
    public void Given_Carts_When_Deleting_Then_DatabaseEmpty() throws Exception {
        loadData();
        for (User user : userList) {
            userService.save(user);
        }
        for (Product product : productList) {
            productService.save(product);
        }
        for (CartDto cartDto : cartDtoList) {
            cartService.add(cartDto);
        }

        for (long i = 1L; i <= cartDtoList.size(); ++i) {
            cartService.delete(i);
        }
        assert cartService.getAllSortedByTotal().size() == 0;
    }
}
