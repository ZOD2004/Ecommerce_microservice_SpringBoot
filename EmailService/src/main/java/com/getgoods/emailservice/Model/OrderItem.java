package com.getgoods.emailservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long id;
    private Long productId;
    private Long quantity;
    private Long unitPrice;
    private Long totalPrice;
}
