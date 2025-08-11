package com.getgoods.orderservice.service;

import com.getgoods.orderservice.entity.Order;
import com.getgoods.orderservice.entity.OrderStatus;

import java.util.List;
import java.util.Date;

public interface OrderService {
    Order createOrderFromCart(Long userId);

    Order getOrderById(Long orderId);
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getAllOrders();


    List<Order> getOrdersByStatus(OrderStatus status);


    void cancelOrder(Long orderId);


    Long getOrderCountByUser(Long userId);
    Long getTotalOrderCount();
    Long getTotalRevenueByUser(Long userId);
    Long getTotalRevenue();

    Long getTotalBill(Long id);


    List<Order> getOrdersByDateRange(Date startDate, Date endDate);

    Long getTotalBillWithCoupon(Long id, String couponcode);
}
