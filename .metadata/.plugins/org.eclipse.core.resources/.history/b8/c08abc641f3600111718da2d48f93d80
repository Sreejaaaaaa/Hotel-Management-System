package com.example.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.entity.Inventory;
import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping
    public Inventory add(@RequestBody Inventory item) {
        return service.addItem(item);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAllItems();
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable int id, @RequestBody Inventory item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteItem(id);
        return "Deleted";
    }
}