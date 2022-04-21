package com.example.dbcsassignmentunittests.repository;

import com.example.dbcsassignmentunittests.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
