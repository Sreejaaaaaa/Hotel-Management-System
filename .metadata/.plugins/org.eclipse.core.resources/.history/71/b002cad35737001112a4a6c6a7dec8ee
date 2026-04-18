package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import com.example.demo.feign.PaymentClient;
import com.example.demo.model.Payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ReportServiceTest {

    @InjectMocks
    private ReportService service;

    @Mock
    private PaymentClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTotalIncome() {

        Payment p1 = mock(Payment.class);
        Payment p2 = mock(Payment.class);

        when(p1.getAmount()).thenReturn(100.0);
        when(p2.getAmount()).thenReturn(200.0);

        when(client.getAllPayments()).thenReturn(List.of(p1, p2));

        double result = service.getTotalIncome();

        assertEquals(300.0, result);
    }

    @Test
    void testGetTotalIncomeEmpty() {

        when(client.getAllPayments()).thenReturn(List.of());

        double result = service.getTotalIncome();

        assertEquals(0.0, result);
    }
}