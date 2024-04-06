package com.example.hypersoft.responsemodel;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@DataAmount
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFailed {
    private Long userId;
    private Long orderId;
    private String transactionId;
    private String status;
    private String description;
}
