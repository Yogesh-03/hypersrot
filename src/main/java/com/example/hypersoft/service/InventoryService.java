package com.example.hypersoft.service;

import com.example.hypersoft.entity.Inventory;

public interface InventoryService {

    public Inventory getInventoryStatus();
    public void updateOrderedItems(int ordered);
}
