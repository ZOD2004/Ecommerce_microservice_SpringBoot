package com.getgoods.cartservice.Util;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message){
        super(message);
    }
}
