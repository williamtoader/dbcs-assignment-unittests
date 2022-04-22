package com.example.dbcsassignmentunittests.service;

import com.example.dbcsassignmentunittests.model.User;
import com.example.dbcsassignmentunittests.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User get(String username){
        return userRepository.getById(username);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }
}
