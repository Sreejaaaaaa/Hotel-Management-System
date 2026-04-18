package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repo;

    public Inventory addItem(Inventory item) {
        return repo.save(item);
    }

    public List<Inventory> getAllItems() {
        return repo.findAll();
    }

    public Inventory updateItem(int id, Inventory item) {
        item.setId(id);
        return repo.save(item);
    }

    public void deleteItem(int id) {
        repo.deleteById(id);
    }
}