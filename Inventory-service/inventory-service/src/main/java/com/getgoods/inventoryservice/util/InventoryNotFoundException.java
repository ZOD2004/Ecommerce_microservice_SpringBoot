package com.getgoods.inventoryservice.util;

public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(String message){
        super(message);
    }
}
