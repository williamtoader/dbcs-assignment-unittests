package com.example.dbcsassignmentunittests.repository;

import com.example.dbcsassignmentunittests.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, String> {
}
