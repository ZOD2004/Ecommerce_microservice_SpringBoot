package com.getgoods.ecommercecoupon.Util;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String message){
        super(message);
    }
}
