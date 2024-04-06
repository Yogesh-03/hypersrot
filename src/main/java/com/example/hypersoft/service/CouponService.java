package com.example.hypersoft.service;

import com.example.hypersoft.entity.Coupon;

import java.util.List;
import java.util.Map;

public interface CouponService {
    public List<Coupon> fetchCoupons();
    public Map<String, Integer> getCouponsMap(List<Coupon> coupons);
}
