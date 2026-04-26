package com.example.billing.service;

import com.example.billing.entity.Bill;
import com.example.billing.repository.BillRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    private Bill bill;

    @BeforeEach
    void setup() {
        bill = new Bill();
        bill.setBookingId(1L);
        bill.setAmount(100.0);
    }

    @Test
    void testGenerateBill() {

        // Mock repository save
        when(billRepository.save(any(Bill.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Bill result = billService.generateBill(bill);

        // Verify calculations
        assertEquals(10.0, result.getTax());              // 10% of 100
        assertEquals(110.0, result.getTotalAmount());     // amount + tax

        // Verify save called
        verify(billRepository, times(1)).save(bill);
    }

    @Test
    void testGenerateBill_zeroAmount() {

        bill.setAmount(0.0);

        when(billRepository.save(any(Bill.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Bill result = billService.generateBill(bill);

        assertEquals(0.0, result.getTax());
        assertEquals(0.0, result.getTotalAmount());
    }
}