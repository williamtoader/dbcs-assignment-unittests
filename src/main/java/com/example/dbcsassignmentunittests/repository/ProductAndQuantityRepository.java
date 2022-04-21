package com.example.dbcsassignmentunittests.repository;

import com.example.dbcsassignmentunittests.model.ProductAndQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAndQuantityRepository extends JpaRepository<ProductAndQuantity, Long> {
}
