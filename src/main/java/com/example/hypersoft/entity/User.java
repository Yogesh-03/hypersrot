package com.example.hypersoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = " cannot be blank")
    private String username;

    @NotBlank(message = " cannot be blank")
    @Column(unique = true)
    private String email;
}
