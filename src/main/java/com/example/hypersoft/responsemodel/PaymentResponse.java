package com.example.hypersoft.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {
    private Long userId;
    private Long orderId;
    private String transactionId;
    private String status;
    private String description;
}
