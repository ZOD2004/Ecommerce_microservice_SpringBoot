package com.getgoods.ecommercecoupon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @Column(unique = true, nullable = false)
    @NotNull(message = "couponCode cannot be null")
    String couponCode;
    @Future(message = "Expiry date must be today or in the future")
    Date expDate;

    @Min(1)
    Long price;

}
