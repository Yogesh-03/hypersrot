package com.example.hypersoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Inventory {
    
    private int ordered = 0;
    private int price = 0;
    private int available = 0;

    public Inventory(int orderedItems, int pricePerItem, int availableItems) {
        this.ordered = orderedItems;
        this.price = pricePerItem;
        this.available = availableItems;
    }
}
