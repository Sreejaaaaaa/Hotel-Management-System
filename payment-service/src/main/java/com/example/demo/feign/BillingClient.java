package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Bill;

@FeignClient(name = "BILLING-SERVICE")   
public interface BillingClient {

    @PostMapping("/bill/generate")
    Bill generateBill(@RequestBody Bill bill);
}