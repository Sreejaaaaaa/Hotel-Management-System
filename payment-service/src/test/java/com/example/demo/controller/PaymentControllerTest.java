package com.example.demo.controller;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    // ✅ TEST CREATE PAYMENT
    @Test
    void testMakePayment() throws Exception {

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", "order_123");
        response.put("amount", 200.0);
        response.put("currency", "INR");
        response.put("paymentId", 1);

        when(paymentService.makePayment(org.mockito.ArgumentMatchers.any()))
                .thenReturn(response);

        String requestJson = """
                {
                    "bookingId": 1,
                    "amount": 200.0
                }
                """;

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("order_123"))
                .andExpect(jsonPath("$.amount").value(200.0))
                .andExpect(jsonPath("$.currency").value("INR"));
    }

    // ✅ TEST GET ALL PAYMENTS
    @Test
    void testGetAllPayments() throws Exception {

        Payment p = new Payment();
        p.setBookingId(1);
        p.setAmount(100.0);
        p.setStatus("SUCCESS");
        p.setTransactionId("txn_123");

        when(paymentService.getAllPayments()).thenReturn(List.of(p));

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookingId").value(1))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].status").value("SUCCESS"))
                .andExpect(jsonPath("$[0].transactionId").value("txn_123"));
    }

    // ✅ TEST GET BY BOOKING ID
    @Test
    void testGetPaymentsByBooking() throws Exception {

        Payment p = new Payment();
        p.setBookingId(2);
        p.setAmount(150.0);
        p.setStatus("SUCCESS");
        p.setTransactionId("txn_456");

        when(paymentService.getPaymentsByBooking(2)).thenReturn(List.of(p));

        mockMvc.perform(get("/payments/booking/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookingId").value(2));
    }

    // ✅ TEST VERIFY PAYMENT SUCCESS
    @Test
    void testVerifyPayment_success() throws Exception {

        when(paymentService.generateSignature("order1", "pay1"))
                .thenReturn("abc123");

        String requestJson = """
                {
                    "orderId": "order1",
                    "paymentId": "pay1",
                    "signature": "abc123"
                }
                """;

        mockMvc.perform(post("/payments/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));
    }

    // ✅ TEST VERIFY PAYMENT FAILURE
    @Test
    void testVerifyPayment_invalidSignature() throws Exception {

        when(paymentService.generateSignature("order1", "pay1"))
                .thenReturn("correct_sig");

        String requestJson = """
                {
                    "orderId": "order1",
                    "paymentId": "pay1",
                    "signature": "wrong_sig"
                }
                """;

        mockMvc.perform(post("/payments/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failed"));
    }

    // ✅ TEST VERIFY PAYMENT EXCEPTION
    @Test
    void testVerifyPayment_exception() throws Exception {

        when(paymentService.generateSignature("order1", "pay1"))
                .thenThrow(new RuntimeException("Error"));

        String requestJson = """
                {
                    "orderId": "order1",
                    "paymentId": "pay1",
                    "signature": "abc"
                }
                """;

        mockMvc.perform(post("/payments/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value("error"));
    }
}