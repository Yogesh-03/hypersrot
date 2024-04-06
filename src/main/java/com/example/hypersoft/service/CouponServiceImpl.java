package com.example.hypersoft.service;

import com.example.hypersoft.entity.Coupon;
import com.example.hypersoft.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> fetchCoupons() {
        return couponRepository.findAll();
    }

    // Optionally, you can add a method to convert List<Coupon> to Map<String, Integer> for the response
    public Map<String, Integer> getCouponsMap(List<Coupon> coupons) {
        return coupons.stream().collect(Collectors.toMap(Coupon::getCouponCode, Coupon::getDiscountPercentage));
    }
}
