package com.getgoods.orderservice.util;

public class OrderNotMadeException extends RuntimeException {
    public OrderNotMadeException(String message) {
        super(message);
    }
}
