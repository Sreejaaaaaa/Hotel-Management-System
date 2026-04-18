package com.example.billing.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void testGettersAndSetters() {

        Bill bill = new Bill();

        bill.setBookingId(1);
        bill.setAmount(200);
        bill.setTax(20);
        bill.setTotal(220);

        assertEquals(1, bill.getBookingId());
        assertEquals(200, bill.getAmount());
        assertEquals(20, bill.getTax());
        assertEquals(220, bill.getTotal());
    }
}