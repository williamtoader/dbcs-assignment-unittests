package com.example.dbcsassignmentunittests.controller;

import com.example.dbcsassignmentunittests.model.Product;
import com.example.dbcsassignmentunittests.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    private Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    private List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    private Product get(@PathVariable String id) {
        return productService.getById(id);
    }

    @DeleteMapping("{id}")
    private String delete(@PathVariable String id) {
        productService.delete(id);
        return "Succefully deleted product " + id;
    }
}
