package com.example.dbcsassignmentunittests.unit;

import com.example.dbcsassignmentunittests.model.User;
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

@SpringBootTest
@Transactional
public class UserCRUDTest {
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    List<User> userList;


    @Test
    @Order(1)
    public void Given_Users_When_AddingThemToDatabase_Then_RetrieveSuccessful() throws Exception {
        userList = objectMapper.readValue(
                ResourceLoader.loadTestData("users"),
                new TypeReference<>() {
                }
        );
        for (User user : userList) {
            userService.save(user);
        }
        for (User user : userList) {
            assert userService.get(user.getUsername()).equals(user);
        }
        for (User user : userService.getAll()) {
            assert userList.contains(user);
        }
    }

    @Test
    @Order(2)
    public void Given_Users_When_UpdatingData_Then_UpdateSuccessful() throws Exception {
        userList = objectMapper.readValue(
                ResourceLoader.loadTestData("users"),
                new TypeReference<>() {
                }
        );
        for (User user : userList) {
            userService.save(user);
        }
        for (User user : userList) {
            user.setAddress("Edited: " + user.getAddress());
            userService.save(user);
        }
        for (User user : userList) {
            assert userService.get(user.getUsername()).equals(user);
        }
    }

    @Test
    @Order(3)
    public void Given_Users_When_Deleting_Then_DatabaseEmpty() throws Exception {
        userList = objectMapper.readValue(
                ResourceLoader.loadTestData("users"),
                new TypeReference<>() {
                }
        );
        for (User user : userList) {
            userService.save(user);
        }
        for (User user : userList) {
            userService.delete(user.getUsername());
        }
        assert userService.getAll().size() == 0;
    }
}
