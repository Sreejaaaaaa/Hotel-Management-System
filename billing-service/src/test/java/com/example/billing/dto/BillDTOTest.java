package com.example.billing.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillDTOTest {

    @Test
    void testGettersAndSetters() {

        BillDTO dto = new BillDTO();

        dto.setBookingId(1L);
        dto.setAmount(100.0);
        dto.setTax(18.0);
        dto.setTotalAmount(118.0);

        assertEquals(1L, dto.getBookingId());
        assertEquals(100.0, dto.getAmount());
        assertEquals(18.0, dto.getTax());
        assertEquals(118.0, dto.getTotalAmount());
    }

    @Test
    void testDefaultValues() {

        BillDTO dto = new BillDTO();

        assertNull(dto.getBookingId());
        assertEquals(0.0, dto.getAmount());
        assertEquals(0.0, dto.getTax());
        assertEquals(0.0, dto.getTotalAmount());
    }
}