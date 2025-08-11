package com.getgoods.productservice.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;
}
