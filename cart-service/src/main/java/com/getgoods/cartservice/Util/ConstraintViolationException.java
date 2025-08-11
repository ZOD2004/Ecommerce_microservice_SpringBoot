package com.getgoods.cartservice.Util;

import jakarta.validation.ConstraintViolation;

public class ConstraintViolationException extends RuntimeException {
    public ConstraintViolationException(){
        super("Cart is empty");
    }


}
