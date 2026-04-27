package com.example.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.billing.dto.BillDTO;
import com.example.billing.entity.Bill;
import com.example.billing.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/generate")
    public ResponseEntity<BillDTO> generateBill(@RequestBody BillDTO dto) {

        Bill bill = new Bill();
        bill.setBookingId(dto.getBookingId());
        bill.setAmount(dto.getAmount());

        Bill saved = billService.generateBill(bill);

        BillDTO response = new BillDTO();
        response.setBookingId(saved.getBookingId());
        response.setAmount(saved.getAmount());
        response.setTax(saved.getTax());
        response.setTotalAmount(saved.getTotalAmount());

        return ResponseEntity.ok(response);
    }
}