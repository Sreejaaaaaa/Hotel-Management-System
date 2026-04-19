package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.entity.Payment;
import com.example.demo.feign.BillingClient;
import com.example.demo.model.Bill;
import com.example.demo.repository.PaymentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository repo;

    @Mock
    private BillingClient billingClient;

    @InjectMocks
    private PaymentService service;

    @Test
    void testMakePaymentSuccess() {

        Payment p = new Payment();
        p.setBookingId(1);
        p.setAmount(1000);

        Bill bill = new Bill();
        bill.setTotalAmount(1200);

        when(billingClient.generateBill(any())).thenReturn(bill);
        when(repo.save(any())).thenReturn(p);

        Payment result = service.makePayment(p);

        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    void testMakePaymentNull() {
        assertThrows(RuntimeException.class, () -> {
            service.makePayment(null);
        });
    }

    @Test
    void testGetAllPayments() {
        when(repo.findAll()).thenReturn(java.util.List.of(new Payment()));

        assertEquals(1, service.getAllPayments().size());
    }

    @Test
    void testGetPaymentsByBooking() {

        Payment p = new Payment();
        p.setBookingId(1);

        when(repo.findAll()).thenReturn(java.util.List.of(p));

        assertEquals(1, service.getPaymentsByBooking(1).size());
    }

    @Test
    void testVerifyPayment() {
        assertEquals("Payment verification simulated successfully", service.verifyPayment());
    }
}