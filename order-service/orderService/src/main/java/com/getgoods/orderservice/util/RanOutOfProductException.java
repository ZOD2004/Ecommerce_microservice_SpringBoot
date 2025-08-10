package com.getgoods.orderservice.util;

public class RanOutOfProductException extends RuntimeException {
    public RanOutOfProductException(String message) {
        super(message);
    }
}
