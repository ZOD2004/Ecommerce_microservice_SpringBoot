package com.getgoods.inventoryservice.controller;

import com.getgoods.inventoryservice.util.ErrorDetail;
import com.getgoods.inventoryservice.util.InventoryNotFoundException;
import com.getgoods.inventoryservice.util.NotEnoughStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleInventoryNotFoundException(InventoryNotFoundException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<ErrorDetail> handelNotEnoughStockException(NotEnoughStockException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
