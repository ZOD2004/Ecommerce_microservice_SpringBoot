package com.getgoods.inventoryservice.service.serviceImple;

import com.getgoods.inventoryservice.entity.Inventory;
import com.getgoods.inventoryservice.model.BulkStock;
import com.getgoods.inventoryservice.model.StockItem;
import com.getgoods.inventoryservice.repository.InventoryRepository;
import com.getgoods.inventoryservice.service.InventoryService;
import com.getgoods.inventoryservice.util.InventoryNotFoundException;
import com.getgoods.inventoryservice.util.NotEnoughStockException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryServiceImple implements InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryServiceImple(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addInventory(Long productId, Long quantity) {
        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long productId, Long quantity) {
        Optional<Inventory> inventory = inventoryRepository.findById(productId);
        if(inventory.isPresent()) {
            Long totQuantity = inventory.get().getQuantity()+quantity;
            inventory.get().setQuantity(totQuantity);
            return inventoryRepository.save(inventory.get());
        }
        else{
            throw new InventoryNotFoundException("Inventory not found with Product Id: " + productId);
        }
    }
    public boolean isInStock(Long productId, Long requiredQty) {
        Optional<Inventory> invOpt = inventoryRepository.findByProductId(productId);
//        System.out.println(productId+"  "+requiredQty);
        if(invOpt.isPresent()) {
            Inventory inventory = invOpt.get();
            if(inventory.getQuantity()>=requiredQty) {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new InventoryNotFoundException("Inventory not found with Product Id: " + productId);
        }
    }

    public Inventory reduceStock(Long productId, Long qty) {
//        System.out.println("Stock to be reduced"+qty);
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("ProductId with "+productId+" not found in inventory"));
        if (inv.getQuantity() < qty) {
            throw new NotEnoughStockException("Not enough stock order less than "+inv.getQuantity());
        }
        inv.setQuantity(inv.getQuantity() + qty);
        return inventoryRepository.save(inv);
    }
    public Long getStock(Long productId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            return inventoryOpt.get().getQuantity();
        } else {
            return 0L;
        }
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(Long id) {
        Optional<Inventory>inv=inventoryRepository.findById(id);
        if(inv.isPresent()) {
            return inv.get();
        }
        else{
            throw new InventoryNotFoundException("Inventory not found with Id: " + id);
        }
    }

    public  Inventory findInventoryByProductId(Long productId) {
        Optional<Inventory>inv=inventoryRepository.findByProductId(productId);
        if(inv.isPresent()) {
            return inv.get();
        }
        else{
            throw new InventoryNotFoundException("Inventory not found with Id: " + productId);
        }
    }

    @Override
    public Inventory addStock(Long productId, Long qty) {
        Optional<Inventory>inv=inventoryRepository.findByProductId(productId);
//        System.out.println("Added stock"+qty);
        if(inv.isPresent()) {
            Inventory inventory = inv.get();
            inventory.setQuantity(inventory.getQuantity() + qty);
            inventoryRepository.save(inventory);
            return inventory;
        }
        else{
            throw new InventoryNotFoundException("Inventory not found with Id: " + productId);
        }
    }

    @Override
    public void reduceInBulk(BulkStock bulkStock) {
        List<StockItem>stockItems = bulkStock.getItems();
        for(StockItem stockItem : stockItems) {
            reduceStock(stockItem.getProductId(), stockItem.getQuantity());
        }
    }

    @Override
    public Boolean checkInBulk(BulkStock bulkStock) {
        Boolean result = Boolean.TRUE;
        for(StockItem stockItem : bulkStock.getItems()) {
            if(!isInStock(stockItem.getProductId(), stockItem.getQuantity())){
                result = Boolean.FALSE;
                break;
            }
        }
        return result;
    }

    @Override
    public void addInBulk(BulkStock bulkStock) {
        List<StockItem>stockItems = bulkStock.getItems();
        for(StockItem stockItem : stockItems) {
            addStock(stockItem.getProductId(), stockItem.getQuantity());
        }
    }

}
