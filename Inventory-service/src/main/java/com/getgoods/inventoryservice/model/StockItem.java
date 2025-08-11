package com.getgoods.inventoryservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockItem {
    private Long productId;
    private Long quantity;
}
