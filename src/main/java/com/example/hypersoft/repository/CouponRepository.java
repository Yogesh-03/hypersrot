package com.example.hypersoft.repository;

import com.example.hypersoft.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {

    @Query(value = "SELECT * FROM coupon WHERE coupon_code = \"OFF5\"", nativeQuery = true)
    Coupon findCouponByCouponCode(@Param("coupon") String coupon);
}
