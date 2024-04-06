package com.example.hypersoft.responsemodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private Long orderId;
    private double amount;
    private String date;
    private String coupon;
    @Id
    private String transactionId;
    private String status;
}
