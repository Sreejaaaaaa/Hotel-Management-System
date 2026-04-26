package com.example.demo.aop;

import com.example.demo.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoggingAspectTest {

    @Autowired
    private TestService testService;

    @Test
    void testSuccessMethod() {
        String result = testService.successMethod();

        // Basic assertion
        assert result.equals("Success");
    }

    @Test
    void testExceptionMethod() {
        try {
            testService.exceptionMethod();
        } catch (Exception e) {
            assert e.getMessage().equals("Test Exception");
        }
    }
}