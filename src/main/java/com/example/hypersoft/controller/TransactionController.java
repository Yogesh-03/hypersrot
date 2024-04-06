package com.example.hypersoft.controller;

import com.example.hypersoft.responsemodel.PaymentResponse;
import com.example.hypersoft.error.TransactionException;
import com.example.hypersoft.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<PaymentResponse> processPayment(@PathVariable Long userId, @PathVariable Long orderId, @RequestParam int amount) throws TransactionException {
        try {

            PaymentResponse response = transactionService.processPayment(userId, orderId, amount);
            return ResponseEntity.ok(response);

        } catch (TransactionException exception) {

            if (exception.getMessage().equals("No response from payment server")) {
                return ResponseEntity.status(504).body(new PaymentResponse(userId,
                        orderId,
                        transactionService.getTransactionId(),
                        "Failed",
                        exception.getMessage()
                ));
            } else if (exception.getMessage().equals("Order is already paid fo")) {
                return ResponseEntity.status(405).body(new PaymentResponse(userId,
                        orderId,
                        transactionService.getTransactionId(),
                        "Failed",
                        exception.getMessage()
                ));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PaymentResponse(userId, orderId, transactionService.getTransactionId(), "Failed", exception.getMessage()));
        }
    }
}
