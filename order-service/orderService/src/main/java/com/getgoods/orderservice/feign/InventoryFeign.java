package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.model.BulkStock;
import com.getgoods.orderservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryFeign {
    @PutMapping("/api/inventory/reduce/{productId}/{qty}")
    public ResponseEntity<Inventory> reduceStock(
            @PathVariable("productId") Long productId,
            @PathVariable("qty") Long qty
    );
    @GetMapping("/api/inventory/is-in-stock/{productId}/{reqQuantity}")
    public ResponseEntity<Boolean> isInStock(
            @PathVariable("productId") Long productId,
            @PathVariable("reqQuantity") Long reqQuantity
    );

    @PutMapping("/api/inventory/bulk-reduce")
    public ResponseEntity<Void> reduceInBulk(@RequestBody BulkStock bulkStock);

    @GetMapping("/api/inventory/bulk-check")
    public ResponseEntity<Boolean> checkInBulk(@RequestBody BulkStock bulkStock);

    @PutMapping("/api/inventory/bulk-add")
    public ResponseEntity<Void> addInBulk(@RequestBody BulkStock bulkStock);
}
