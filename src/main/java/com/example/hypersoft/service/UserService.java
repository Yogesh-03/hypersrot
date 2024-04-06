package com.example.hypersoft.service;

import com.example.hypersoft.entity.User;
import com.example.hypersoft.error.UserException;
import org.springframework.stereotype.Service;


public interface UserService {

    public void createUser(User user) throws UserException;
    public User getUserById(Long userId) throws UserException;
}
