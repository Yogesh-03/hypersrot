package com.example.hypersoft.service;

import com.example.hypersoft.responsemodel.PaymentResponse;
import com.example.hypersoft.error.TransactionException;

public interface TransactionService {
    public PaymentResponse processPayment(Long userId, Long orderId, int amount) throws TransactionException;
    public String getTransactionId();
}
