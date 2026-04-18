package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testConstructorsAndGettersSetters() {

        Payment p = new Payment();

        p.setBookingId(1);
        p.setAmount(1000);
        p.setStatus("SUCCESS");

        // Manually set ID using reflection (since no setter)
        try {
            java.lang.reflect.Field field = Payment.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(p, 10);
        } catch (Exception e) {
            fail("Reflection failed");
        }

        assertEquals(10, p.getId());   // ✅ cover getId()
        assertEquals(1, p.getBookingId());
        assertEquals(1000, p.getAmount());
        assertEquals("SUCCESS", p.getStatus());

        Payment p2 = new Payment(2, 2000, "FAILED");

        assertEquals(2, p2.getBookingId());
        assertEquals(2000, p2.getAmount());
        assertEquals("FAILED", p2.getStatus());
    }
}