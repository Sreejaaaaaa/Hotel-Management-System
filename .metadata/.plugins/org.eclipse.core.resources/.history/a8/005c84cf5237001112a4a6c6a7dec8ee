package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Method;

class PaymentServiceTest {

    @InjectMocks
    private PaymentService service;

    @Mock
    private PaymentRepository repo;

    private Payment payment;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        payment = new Payment(1, 1000, "SUCCESS");

        // Inject mock manually (since no @Autowired / constructor)
        java.lang.reflect.Field field =
                PaymentService.class.getDeclaredField("paymentRepository");

        field.setAccessible(true);
        field.set(service, repo);
    }

    @Test
    void testMakePaymentUsingReflection() throws Exception {

        when(repo.save(payment)).thenReturn(payment);

        // Access private method
        Method method = PaymentService.class
                .getDeclaredMethod("makePayment", Payment.class);

        method.setAccessible(true);

        Payment result = (Payment) method.invoke(service, payment);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());

        verify(repo, times(1)).save(payment);
    }

    @Test
    void testMakePaymentWithNull() throws Exception {

        when(repo.save(null)).thenReturn(null);

        Method method = PaymentService.class
                .getDeclaredMethod("makePayment", Payment.class);

        method.setAccessible(true);

        Payment result = (Payment) method.invoke(service, (Object) null);

        assertNull(result);
        verify(repo, times(1)).save(null);
    }
}