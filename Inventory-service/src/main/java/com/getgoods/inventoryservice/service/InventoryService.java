package com.getgoods.inventoryservice.service;

import com.getgoods.inventoryservice.entity.Inventory;
import com.getgoods.inventoryservice.model.BulkStock;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    Inventory addInventory(Long productId, Long quantity);

    Inventory updateInventory(Long productId, Long quantity);

    boolean isInStock(Long productId, Long requiredQty);

    Inventory reduceStock(Long productId, Long qty);

    Long getStock(Long productId);

    List<Inventory> findAll();

    Inventory findInventoryById(Long id);

    Inventory findInventoryByProductId(Long productId);

    Inventory addStock(Long productId, Long qty);

    void reduceInBulk(BulkStock bulkStock);

    Boolean checkInBulk(BulkStock bulkStock);

    void addInBulk(BulkStock bulkStock);

}
