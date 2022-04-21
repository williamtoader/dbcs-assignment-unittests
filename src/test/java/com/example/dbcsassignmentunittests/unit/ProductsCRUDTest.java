package com.example.dbcsassignmentunittests.unit;

import com.example.dbcsassignmentunittests.model.ProductAndQuantity;
import com.example.dbcsassignmentunittests.repository.ProductAndQuantityRepository;
import com.example.dbcsassignmentunittests.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductsCRUDTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductAndQuantityRepository productAndQuantityRepository;

    @Test
    public void when_Debugging() throws Exception {
        System.out.println("Breakpoint here");
        throw new Exception("fail");
    }
}
