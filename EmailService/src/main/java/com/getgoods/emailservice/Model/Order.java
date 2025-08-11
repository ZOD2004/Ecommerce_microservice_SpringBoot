package com.getgoods.emailservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderId;
    private Long userId;
    private String status;
    private Long totalAmount;
    private Date orderDate;
    private Date updatedAt;
    private List<OrderItem> orderItems;
    private Long cartId;
    private String address;
}
