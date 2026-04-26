package com.example.billing.entity;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BillEntityTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
    void testValidBill() {

        Bill bill = new Bill();
        bill.setBookingId(1L);
        bill.setAmount(100.0);

        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testBookingIdNull() {

        Bill bill = new Bill();
        bill.setAmount(100.0);

        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        assertFalse(violations.isEmpty());
    }

    @Test
    void testAmountNegative() {

        Bill bill = new Bill();
        bill.setBookingId(1L);
        bill.setAmount(-50.0);

        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        assertFalse(violations.isEmpty());
    }
}