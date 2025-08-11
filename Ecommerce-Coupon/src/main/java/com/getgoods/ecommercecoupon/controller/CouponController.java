package com.getgoods.ecommercecoupon.controller;

import com.getgoods.ecommercecoupon.model.Coupon;
import com.getgoods.ecommercecoupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @GetMapping("/find")
    public ResponseEntity<List<Coupon>> findAll(){
        return new ResponseEntity<>(couponService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/code/{coupon-code}")
    public ResponseEntity<Coupon> findByCouponCode(@PathVariable("coupon-code") String couponCode){
        return new ResponseEntity<>(couponService.findByCouponCode(couponCode), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Coupon> add(@RequestBody Coupon coupon){
        return new ResponseEntity<>(couponService.addCoupon(coupon),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Coupon> updateById(@PathVariable("id") Long id,@RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.updateById(id,coupon),HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Coupon> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(couponService.findById(id),HttpStatus.OK);
    }

}
