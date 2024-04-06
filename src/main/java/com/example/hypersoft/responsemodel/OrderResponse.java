package com.example.hypersoft.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private int quantity;
    private int amount;
    private String coupon;
}
