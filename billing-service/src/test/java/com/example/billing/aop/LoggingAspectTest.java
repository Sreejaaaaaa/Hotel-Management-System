package com.example.billing.aop;

import com.example.billing.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoggingAspectTest {

    @Autowired
    private TestService testService;

    @Test
    void testAspectExecution() {
        testService.testMethod(); // 🔥 triggers @Before and @After
    }
}