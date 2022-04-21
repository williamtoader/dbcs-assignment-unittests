package com.example.dbcsassignmentunittests.repository;

import com.example.dbcsassignmentunittests.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
