package com.example.demo.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDTO() {
        PaymentDTO dto = new PaymentDTO();
        dto.setBookingId(1);
        dto.setAmount(100.0);

        Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testBookingIdNull() {
        PaymentDTO dto = new PaymentDTO();
        dto.setAmount(100.0);

        Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void testAmountNegative() {
        PaymentDTO dto = new PaymentDTO();
        dto.setBookingId(1);
        dto.setAmount(-50.0);

        Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}