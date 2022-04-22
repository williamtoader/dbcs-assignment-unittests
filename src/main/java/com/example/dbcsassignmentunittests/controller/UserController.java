package com.example.dbcsassignmentunittests.controller;

import com.example.dbcsassignmentunittests.model.User;
import com.example.dbcsassignmentunittests.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    private User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping
    private List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    private User get(@PathVariable String id) {
        return userService.get(id);
    }

    @DeleteMapping("{id}")
    private String delete(@PathVariable String id) {
        userService.delete(id);
        return "Succesfuly deleted.";
    }
}
