package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleValidation() {

        BindingResult bindingResult = mock(BindingResult.class);

        List<FieldError> errors = List.of(
                new FieldError("room", "type", "Room type is required"),
                new FieldError("room", "price", "Price must be greater than 0")
        );

        when(bindingResult.getFieldErrors()).thenReturn(errors);

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        Map<String, String> result = handler.handleValidation(ex);

        assertEquals(2, result.size());
        assertEquals("Room type is required", result.get("type"));
        assertEquals("Price must be greater than 0", result.get("price"));
    }

    @Test
    void testHandleValidationEmpty() {

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of());

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        Map<String, String> result =
                new GlobalExceptionHandler().handleValidation(ex);

        assertTrue(result.isEmpty());
    }
}