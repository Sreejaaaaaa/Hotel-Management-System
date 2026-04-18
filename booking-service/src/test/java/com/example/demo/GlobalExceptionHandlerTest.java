package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleValidation() {

        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        BindingResult result = new BeanPropertyBindingResult(new Object(), "obj");
        result.addError(new FieldError("obj", "field", "error"));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, result);

        Map<String, String> errors = handler.handleValidation(ex);

        assertEquals("error", errors.get("field"));
    }
}