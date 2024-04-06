package com.example.hypersoft.controller;

import com.example.hypersoft.entity.Inventory;
import com.example.hypersoft.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory = inventoryService.getInventoryStatus();
        return ResponseEntity.ok(inventory);
    }
}
