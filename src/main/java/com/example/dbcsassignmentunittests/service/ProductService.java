package com.example.dbcsassignmentunittests.service;

import com.example.dbcsassignmentunittests.model.Product;
import com.example.dbcsassignmentunittests.repository.ProductAndQuantityRepository;
import com.example.dbcsassignmentunittests.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductAndQuantityRepository productAndQuantityRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(String productCode) {
        return productRepository.getById(productCode);
    }

    public void delete(String productCode) {
        productRepository.deleteById(productCode);
    }
}
