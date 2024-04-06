package com.example.hypersoft.service;

import com.example.hypersoft.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private int orderedItems = 0;
    private final int pricePerItem = 100;
    private final int totalItems = 100;

    @Override
    public Inventory getInventoryStatus() {
        int availableItems = totalItems - orderedItems;
        return new Inventory(orderedItems, pricePerItem, availableItems);
    }

    @Override
    public void updateOrderedItems(int ordered) {
        this.orderedItems = ordered;
    }
}
