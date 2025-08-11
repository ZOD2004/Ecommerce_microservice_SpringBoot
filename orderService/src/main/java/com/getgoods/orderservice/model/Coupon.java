package com.getgoods.orderservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    String couponCode;
    Date expDate;
    Long price;
}
