package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.feign.PaymentClient;
import com.example.demo.model.Payment;

@Service
public class ReportService {

    @Autowired
    private PaymentClient paymentClient;

    public double getTotalIncome() {

        List<Payment> payments = paymentClient.getAllPayments();

        return payments.stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}