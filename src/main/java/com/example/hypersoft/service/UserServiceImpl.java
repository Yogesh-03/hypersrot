package com.example.hypersoft.service;

import com.example.hypersoft.entity.User;
import com.example.hypersoft.error.UserException;
import com.example.hypersoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) throws UserException {

        // Check if the user already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException("Email already exists");
        }

        userRepository.save(user);
    }

    public User getUserById(Long userId) throws UserException {
        // Retrieve the user by userId
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User Not found"));
    }
}
