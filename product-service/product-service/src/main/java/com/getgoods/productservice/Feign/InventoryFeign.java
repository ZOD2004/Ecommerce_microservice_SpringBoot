package com.getgoods.productservice.Feign;

import com.getgoods.productservice.Model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryFeign {
    @PostMapping("/api/inventory/add")
    ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory);

}
