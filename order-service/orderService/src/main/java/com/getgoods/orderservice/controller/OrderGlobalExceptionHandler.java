package com.getgoods.orderservice.controller;

import com.getgoods.orderservice.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class OrderGlobalExceptionHandler extends Exception{
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDetail> cartNotFoundExceptionHandler(CartNotFoundException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDetail> orderNotFoundExceptionHandler(OrderNotFoundException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotMadeException.class)
    public ResponseEntity<ErrorDetail> orderNotMadeExceptionHandler(OrderNotMadeException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RanOutOfProductException.class)
    public ResponseEntity<ErrorDetail> orderRanOutOfProductExceptionHandler(RanOutOfProductException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> exceptionHandler(Exception e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetail> userNotFoundExceptionHandler(UserNotFoundException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
