package com.getgoods.ecommercecoupon.service;


import com.getgoods.ecommercecoupon.model.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> findAll();

    Coupon findByCouponCode(String  couponCode);

    Coupon addCoupon(Coupon coupon);

    Coupon updateById(Long id, Coupon coupon);

    Coupon findById(Long id);
}
