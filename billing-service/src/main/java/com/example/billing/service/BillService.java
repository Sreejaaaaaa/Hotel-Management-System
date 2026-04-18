package com.example.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.billing.entity.Bill;
import com.example.billing.repository.BillRepository;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill generateBill(Bill bill) {
        double tax = bill.getAmount() * 0.1;
        bill.setTax(tax);
        bill.setTotalAmount(bill.getAmount() + tax);

        return billRepository.save(bill);
    }
}