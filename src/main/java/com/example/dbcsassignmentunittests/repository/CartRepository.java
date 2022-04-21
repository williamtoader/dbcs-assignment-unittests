package com.example.dbcsassignmentunittests.repository;

import com.example.dbcsassignmentunittests.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
