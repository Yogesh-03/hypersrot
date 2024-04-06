package com.example.hypersoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    private String transactionId;
    private Long userId;
    private Long orderId;
    private double amount;
    private String status;
    private String description;
    private Date date;
}
