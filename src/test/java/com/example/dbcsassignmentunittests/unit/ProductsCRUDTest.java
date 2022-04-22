package com.example.dbcsassignmentunittests.unit;

import com.example.dbcsassignmentunittests.model.Product;
import com.example.dbcsassignmentunittests.service.ProductService;
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
public class ProductsCRUDTest {
    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    private List<Product> productList;

    @Test
    @Order(1)
    public void Given_Products_When_AddingToDatabase_Then_RetrievalSuccessful() throws Exception {
        productList = objectMapper.readValue(
                ResourceLoader.loadTestData("products"),
                new TypeReference<>() {
                }
        );
        for (Product product : productList) {
            assert productService.save(product).equals(product);
        }
        for (Product product : productList) {
            assert productService.getById(product.getCode()).equals(product);
        }
        for (Product product : productService.getAll()) {
            assert productList.contains(product);
        }
    }

    @Test
    @Order(2)
    public void Given_Products_When_UpdatingData_Then_UpdateSuccessful() throws Exception {
        productList = objectMapper.readValue(
                ResourceLoader.loadTestData("products"),
                new TypeReference<>() {
                }
        );
        for (Product product : productList) {
            assert productService.save(product).equals(product);
        }
        for (Product product : productList) {
            product.setName("TEST-" + product.getName());
            assert productService.save(product).equals(product);
        }
        for (Product product : productList) {
            assert productService.getById(product.getCode()).equals(product);
        }
        for (Product product : productService.getAll()) {
            assert productList.contains(product);
        }
    }

    @Test
    @Order(3)
    public void Given_Products_When_Deleting_Then_DatabaseEmpty() throws Exception {
        productList = objectMapper.readValue(
                ResourceLoader.loadTestData("products"),
                new TypeReference<>() {
                }
        );
        for (Product product : productList) {
            assert productService.save(product).equals(product);
        }
        for (Product product : productList) {
            productService.delete(product.getCode());
        }
        assert productService.getAll().size() == 0;
    }
}
