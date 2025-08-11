package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COUPON-SERVICE")
public interface CouponFeign {
    @GetMapping("/api/coupon/find/code/{coupon-code}")
    public ResponseEntity<Coupon> findByCouponCode(@PathVariable("coupon-code") String couponCode);
}
