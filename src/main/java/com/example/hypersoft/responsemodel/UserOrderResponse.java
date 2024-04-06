package com.example.hypersoft.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderResponse {
    private Long orderId;
    private double amount;
    private String date;
    private String coupon;
}
