package com.example.demo.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private SecurityFilterChain filterChain;

    @Test
    void testFilterChainNotNull() {
        assertNotNull(filterChain);
    }
    @Test
    void testSecurityBean() {
        SecurityConfig config = new SecurityConfig();
        assertNotNull(config);
    }
}