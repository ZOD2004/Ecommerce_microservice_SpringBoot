package com.getgoods.ecommercecoupon.controller;

import com.getgoods.ecommercecoupon.Util.CouponNotFoundException;
import com.getgoods.ecommercecoupon.Util.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CouponGlobalExceptionHandler extends Exception{

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleCouponNotFoundException(CouponNotFoundException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(),request.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
