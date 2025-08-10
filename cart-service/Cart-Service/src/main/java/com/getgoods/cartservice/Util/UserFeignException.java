package com.getgoods.cartservice.Util;

public class UserFeignException extends RuntimeException{
    public UserFeignException(String message){
        super(message);
    }
}
