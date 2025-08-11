package com.getgoods.ecommercecoupon.repository;

import com.getgoods.ecommercecoupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    public Optional<Coupon> findByCouponCode(String couponCode);
}
