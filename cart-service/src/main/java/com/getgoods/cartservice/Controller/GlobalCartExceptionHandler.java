package com.getgoods.cartservice.Controller;

import com.getgoods.cartservice.Util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalCartExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFoundException ex, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),req.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductUnavailableException.class)
    public ResponseEntity<ErrorDetails> handleProductUnavailable(ProductUnavailableException ex,WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),req.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCartNotFound(CartNotFoundException ex,WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),req.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCartItemNotFound(CartItemNotFoundException ex,WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),req.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleOtherExceptions(Exception ex,WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),req.getDescription(true),new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

