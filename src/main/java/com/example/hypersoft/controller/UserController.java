package com.example.hypersoft.controller;

import com.example.hypersoft.entity.User;
import com.example.hypersoft.error.UserException;
import com.example.hypersoft.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid  @RequestBody User user, BindingResult result) throws UserException {
        if (result.hasErrors()) {
            // If there are validation errors, return a custom error message
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + " " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        try {
            userService.createUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (RuntimeException e){
            throw new UserException();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) throws UserException{
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("description", e.getMessage()));
        }
    }
}
