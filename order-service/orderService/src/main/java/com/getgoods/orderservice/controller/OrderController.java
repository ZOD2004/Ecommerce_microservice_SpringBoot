package com.getgoods.orderservice.controller;

import com.getgoods.orderservice.entity.Order;
import com.getgoods.orderservice.entity.OrderStatus;
import com.getgoods.orderservice.service.orderServiceImple.OrderServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderServiceImple orderServiceImple;

    public OrderController(OrderServiceImple orderServiceImple) {
        this.orderServiceImple = orderServiceImple;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Order> addOrder(@PathVariable("userId") Long userId,@RequestBody String address) {
        return new ResponseEntity<>(orderServiceImple.createOrderFromCart(userId,address), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderServiceImple.getOrderById(orderId),HttpStatus.OK);
    }

    @GetMapping("/find/users/{id}")
    public ResponseEntity<List<Order>> findOrdersByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(orderServiceImple.getOrdersByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Order>> findAllOrders() {
        return new ResponseEntity<>(orderServiceImple.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/find/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status){
        return new ResponseEntity<>(orderServiceImple.getOrdersByStatus(OrderStatus.valueOf(status)),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable("orderId") Long orderId){
        orderServiceImple.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/count/{id}")
    public ResponseEntity<Long> getOrderCountByUser(@PathVariable("id") Long userId){
        return new ResponseEntity<>(orderServiceImple.getOrderCountByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/find/count")
    public  ResponseEntity<Long> getTotalOrderCount(){
        return new ResponseEntity<>(orderServiceImple.getTotalOrderCount(),HttpStatus.OK);
    }

    @GetMapping("/find/rev/users/{id}")
    public ResponseEntity<Long> getTotalRevenueByUser(@PathVariable("id") Long userId){
        return new ResponseEntity<>(orderServiceImple.getTotalRevenueByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/find/rev")
    public ResponseEntity<Long> getTotalRevenue(){
        return new ResponseEntity<>(orderServiceImple.getTotalRevenue(),HttpStatus.OK);
    }

//    @GetMapping("/find/date/{startDate}/{endDate}")
//    public ResponseEntity<List<Order>> getOrdersByDateRange(@PathVariable("startDate") Date startDate,@PathVariable("endDate") Date endDate){
//        return new ResponseEntity<>(orderServiceImple.getOrdersByDateRange(startDate,endDate),HttpStatus.OK);
//    }


}
