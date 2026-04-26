package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.feign.BillingClient;
import com.example.demo.model.Bill;
import com.example.demo.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BillingClient billingClient;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    void setup() {
        payment = new Payment();
        payment.setBookingId(1);
        payment.setAmount(100.0);
    }

    @Test
    void testMakePayment_failedPath_noRazorpayCall() {

        // Step 1: Mock billing (works normally)
        Bill bill = new Bill();
        bill.setTotalAmount(200.0);
        when(billingClient.generateBill(any(Bill.class))).thenReturn(bill);

        // Step 2: Mock save (no ID handling needed)
        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Step 3: Call method (Razorpay will fail → goes to catch block)
        Map<String, Object> result = paymentService.makePayment(payment);

        // Step 4: Assertions (based on FAILED flow)
        assertNotNull(result);
        assertEquals("FAILED_TXN", result.get("orderId"));
        assertEquals(200.0, result.get("amount"));
        assertEquals("INR", result.get("currency"));
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(List.of(new Payment(), new Payment()));
        assertEquals(2, paymentService.getAllPayments().size());
    }

    @Test
    void testGetPaymentsByBooking() {
        Payment p1 = new Payment();
        p1.setBookingId(1);

        Payment p2 = new Payment();
        p2.setBookingId(2);

        when(paymentRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Payment> result = paymentService.getPaymentsByBooking(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getBookingId());
    }
}