package com.getgoods.cartservice.Feign;

import com.getgoods.cartservice.Model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryFeign {
    @GetMapping("/api/inventory/find/{id}")
    public ResponseEntity<Inventory> findInventoryById(@PathVariable("id") Long id);


    @GetMapping("/api/inventory/find/product/{productId}")
    public ResponseEntity<Inventory> findInventoryByProductId(@PathVariable("productId") Long productId);

    @GetMapping("/api/inventory/is-in-stock/{productId}/{reqQuantity}")
    public ResponseEntity<Boolean> isInStock(
            @PathVariable("productId") Long productId,
            @PathVariable("reqQuantity") Long reqQuantity
    );

    @PutMapping("/api/inventory/reduce/{productId}/{qty}")
    public ResponseEntity<Inventory> reduceStock(
            @PathVariable("productId") Long productId,
            @PathVariable("qty") Long qty
    );

    @PostMapping("/api/inventory/add-stock/{productId}/{qty}")
    public ResponseEntity<Inventory> addStock(@PathVariable("productId") Long productId,
                                              @PathVariable("qty") Long qty);
}
