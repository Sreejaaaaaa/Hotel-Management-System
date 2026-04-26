package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void testGettersAndSetters() {

        Bill bill = new Bill();

        bill.setBillId(1L);
        bill.setBookingId(101L);
        bill.setAmount(100.0);
        bill.setTax(18.0);
        bill.setTotalAmount(118.0);

        assertEquals(1L, bill.getBillId());
        assertEquals(101L, bill.getBookingId());
        assertEquals(100.0, bill.getAmount());
        assertEquals(18.0, bill.getTax());
        assertEquals(118.0, bill.getTotalAmount());
    }

    @Test
    void testDefaultValues() {
        Bill bill = new Bill();

        assertNull(bill.getBillId());
        assertNull(bill.getBookingId());
        assertEquals(0.0, bill.getAmount());
        assertEquals(0.0, bill.getTax());
        assertEquals(0.0, bill.getTotalAmount());
    }
}