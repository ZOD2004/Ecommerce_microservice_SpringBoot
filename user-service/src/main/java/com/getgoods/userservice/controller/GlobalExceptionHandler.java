package com.getgoods.userservice.controller;

import com.getgoods.userservice.util.ErrorDetails;
import com.getgoods.userservice.util.PasswordWrongException;
import com.getgoods.userservice.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<ErrorDetails> handlePasswordWrongException(PasswordWrongException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
