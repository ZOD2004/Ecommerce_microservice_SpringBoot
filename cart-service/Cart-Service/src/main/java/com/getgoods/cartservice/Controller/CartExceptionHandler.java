//package com.getgoods.cartservice.Controller;
//
//
//import com.getgoods.cartservice.Util.*;
//
//import jakarta.validation.ConstraintViolation;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class CartExceptionHandler {
//
//    @ExceptionHandler(CartNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleCartNotFound(CartNotFoundException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
//
//    @ExceptionHandler(CartItemNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleCartItemNotFound(CartItemNotFoundException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
//
//    @ExceptionHandler(ProductUnavailableException.class)
//    public ResponseEntity<ErrorResponse> handleProductUnavailable(ProductUnavailableException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(InventoryNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleInventoryNotFound(InventoryNotFoundException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(UserFeignException.class)
//    public ResponseEntity<ErrorResponse> handleUserFeignException(UserFeignException e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.SERVICE_UNAVAILABLE.value(),
//                "User service is temporarily unavailable: " + e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
//    }
//
//    // Handle validation errors for @RequestParam, @PathVariable
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> handleValidationErrors(ConstraintViolationException e) {
//        Map<String, String> errors = new HashMap<>();
//
//
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                "Validation failed: " + errors.toString(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    // Handle validation errors for @RequestBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
//        Map<String, String> errors = new HashMap<>();
//
//        e.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                "Validation failed: " + errors.toString(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }
//
//    // Generic exception handler
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An unexpected error occurred: " + e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    // Inner class for error response structure
//    public static class ErrorResponse {
//        private int status;
//        private String message;
//        private LocalDateTime timestamp;
//
//        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
//            this.status = status;
//            this.message = message;
//            this.timestamp = timestamp;
//        }
//
//        // Getters and setters
//        public int getStatus() { return status; }
//        public void setStatus(int status) { this.status = status; }
//
//        public String getMessage() { return message; }
//        public void setMessage(String message) { this.message = message; }
//
//        public LocalDateTime getTimestamp() { return timestamp; }
//        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
//    }
//}
