package com.example.hypersoft.responsemodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private int quantity;
    private String coupon;
}
