package com.getgoods.inventoryservice.controller;

import com.getgoods.inventoryservice.entity.Inventory;
import com.getgoods.inventoryservice.model.BulkStock;
import com.getgoods.inventoryservice.service.serviceImple.InventoryServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private InventoryServiceImple inventoryServiceImple;

    public InventoryController(InventoryServiceImple inventoryServiceImple) {
        this.inventoryServiceImple = inventoryServiceImple;
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(
            @RequestBody Inventory inventory
    ){
        return new ResponseEntity<>(inventoryServiceImple.addInventory(inventory.getProductId(), inventory.getQuantity()), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Inventory> updateInventory(
            @RequestParam Long productId,
            @RequestParam Long quantity
    ){
        return new ResponseEntity<>(inventoryServiceImple.updateInventory(productId, quantity), HttpStatus.OK);
    }

    @GetMapping("/is-in-stock/{productId}/{reqQuantity}")
    public ResponseEntity<Boolean> isInStock(
            @PathVariable("productId") Long productId,
            @PathVariable("reqQuantity") Long reqQuantity
    ){
        return new ResponseEntity<>(inventoryServiceImple.isInStock(productId, reqQuantity), HttpStatus.OK);
    }

    @PutMapping("/reduce/{productId}/{qty}")
    public ResponseEntity<Inventory> reduceStock(
            @PathVariable("productId") Long productId,
            @PathVariable("qty") Long qty
    ){
        return new ResponseEntity<>(inventoryServiceImple.reduceStock(productId,qty),HttpStatus.OK);
    }

    @GetMapping("/get-stock/{productId}")
    public ResponseEntity<Long> getStock(
            @PathVariable Long productId
    ){
        return new ResponseEntity<>(inventoryServiceImple.getStock(productId),HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<List<Inventory>> findAll() {
        return new ResponseEntity<>(inventoryServiceImple.findAll(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Inventory> findInventoryById(@PathVariable Long id){
        return new ResponseEntity<>(inventoryServiceImple.findInventoryById(id),HttpStatus.OK);
    }

    @GetMapping("/find/product/{productId}")
    public ResponseEntity<Inventory> findInventoryByProductId(@PathVariable Long productId){
        return  new ResponseEntity<>(inventoryServiceImple.findInventoryByProductId(productId),HttpStatus.OK);
    }

    @PostMapping("/add-stock/{productId}/{qty}")
    public ResponseEntity<Inventory> addStock(@PathVariable("productId") Long productId,
                                              @PathVariable("qty") Long qty){
        return new ResponseEntity<>(inventoryServiceImple.addStock(productId,qty),HttpStatus.OK);
    }

    @PutMapping("/bulk-reduce")
    public ResponseEntity<Void> reduceInBulk(@RequestBody BulkStock bulkStock){
        inventoryServiceImple.reduceInBulk(bulkStock);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bulk-check")
    public ResponseEntity<Boolean> checkInBulk(@RequestBody BulkStock bulkStock){
        return new  ResponseEntity<>(inventoryServiceImple.checkInBulk(bulkStock),HttpStatus.OK);

    }

    @PutMapping("/bulk-add")
    public ResponseEntity<Void> addInBulk(@RequestBody BulkStock bulkStock) {
        inventoryServiceImple.addInBulk(bulkStock);
        return ResponseEntity.ok().build();
    }


}
