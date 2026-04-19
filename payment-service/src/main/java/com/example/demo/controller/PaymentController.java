package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Payment;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.service.PaymentService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public PaymentDTO makePayment(@Valid @RequestBody PaymentDTO dto) {

        log.info("Processing payment request: {}", dto);

        Payment payment = new Payment();
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());

        Payment saved = paymentService.makePayment(payment);

        return convertToDTO(saved);
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {

        log.info("Fetching all payments");

        return paymentService.getAllPayments()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/booking/{bookingId}")
    public List<PaymentDTO> getPaymentsByBooking(@PathVariable int bookingId) {

        log.info("Fetching payments for booking ID: {}", bookingId);

        return paymentService.getPaymentsByBooking(bookingId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

 
    @PostMapping("/verify")
    public String verifyPayment() {

        log.info("Verifying payment (simulation)");

        return paymentService.verifyPayment();
    }

    private PaymentDTO convertToDTO(Payment p) {

        PaymentDTO dto = new PaymentDTO();
        dto.setBookingId(p.getBookingId());
        dto.setAmount(p.getAmount());
        dto.setStatus(p.getStatus());
        dto.setTransactionId(p.getTransactionId());

        return dto;
    }
}