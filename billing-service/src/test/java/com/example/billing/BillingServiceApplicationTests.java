package com.example.billing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BillingServiceApplicationTest {

    @Test
    void contextLoads() {
        // Covers Spring Boot context startup
    }

    @Test
    void mainMethodRuns() {
        BillingServiceApplication.main(new String[]{});
    }
}