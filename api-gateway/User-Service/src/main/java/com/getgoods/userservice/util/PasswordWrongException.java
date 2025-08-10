package com.getgoods.userservice.util;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(String message){
        super(message);
    }
}
