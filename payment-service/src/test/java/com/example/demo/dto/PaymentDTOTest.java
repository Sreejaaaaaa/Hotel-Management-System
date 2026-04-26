package com.example.demo.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentDTOTest {

    @Test
    void testGettersAndSetters() {
        PaymentDTO dto = new PaymentDTO();

        dto.setBookingId(101);
        dto.setAmount(500.0);
        dto.setStatus("SUCCESS");
        dto.setTransactionId("TXN123");

        assertEquals(101, dto.getBookingId());
        assertEquals(500.0, dto.getAmount());
        assertEquals("SUCCESS", dto.getStatus());
        assertEquals("TXN123", dto.getTransactionId());
    }
}