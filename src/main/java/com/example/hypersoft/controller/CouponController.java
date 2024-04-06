package com.example.hypersoft.controller;

import com.example.hypersoft.entity.Coupon;
import com.example.hypersoft.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        List<Coupon> coupons = couponService.fetchCoupons();
        Map<String, Integer> couponsMap = couponService.getCouponsMap(coupons);
        return ResponseEntity.ok(couponsMap);
    }
}
