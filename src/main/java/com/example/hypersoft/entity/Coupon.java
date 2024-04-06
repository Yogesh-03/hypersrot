package com.example.hypersoft.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coupon {

    @Id
    private String couponCode;
    private int discountPercentage;
}
