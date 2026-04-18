package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testGetters() {

        Payment p = new Payment();

        // set using reflection (no setters available)
        try {
            java.lang.reflect.Field f1 = Payment.class.getDeclaredField("id");
            java.lang.reflect.Field f2 = Payment.class.getDeclaredField("bookingId");
            java.lang.reflect.Field f3 = Payment.class.getDeclaredField("amount");
            java.lang.reflect.Field f4 = Payment.class.getDeclaredField("status");

            f1.setAccessible(true);
            f2.setAccessible(true);
            f3.setAccessible(true);
            f4.setAccessible(true);

            f1.set(p, 1);
            f2.set(p, 10);
            f3.set(p, 500.0);
            f4.set(p, "SUCCESS");

        } catch (Exception e) {
            fail("Reflection failed");
        }

        assertEquals(1, p.getId());
        assertEquals(10, p.getBookingId());
        assertEquals(500.0, p.getAmount());
        assertEquals("SUCCESS", p.getStatus());
    }
}