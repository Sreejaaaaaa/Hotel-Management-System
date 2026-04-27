package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.demo.entity.Payment;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.service.PaymentService;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Map<String, Object> makePayment(@Valid @RequestBody PaymentDTO dto) {

        log.info("Processing payment request: {}", dto);

        Payment payment = new Payment();
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());

        return paymentService.makePayment(payment);
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {

        return paymentService.getAllPayments()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/booking/{bookingId}")
    public List<PaymentDTO> getPaymentsByBooking(@PathVariable int bookingId) {

        return paymentService.getPaymentsByBooking(bookingId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyPayment(@RequestBody Map<String, String> payload) {

        String orderId = payload.get("orderId");
        String paymentId = payload.get("paymentId");
        String signature = payload.get("signature");

        try {
            String generatedSignature = paymentService.generateSignature(orderId, paymentId);

            if (generatedSignature.equals(signature)) {
                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Payment verified"
                ));
            } else {
                return ResponseEntity.status(400).body(Map.of(
                        "status", "failed",
                        "message", "Invalid signature"
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body(Map.of(
                    "status", "error",
                    "message", "Verification failed"
            ));
        }
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