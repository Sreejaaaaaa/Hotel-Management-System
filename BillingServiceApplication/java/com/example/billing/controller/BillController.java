package com.example.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.billing.entity.Bill;
import com.example.billing.service.BillingService;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillingService service;

    @PostMapping
    public Bill generate(@RequestBody Bill bill) {
        return service.generateBill(bill);
    }

    @GetMapping
    public List<Bill> getAll() {
        return service.getAllBills();
    }
}