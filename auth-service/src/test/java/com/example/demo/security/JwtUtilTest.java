package com.example.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() throws Exception {
        jwtUtil = new JwtUtil();

        Field secret = JwtUtil.class.getDeclaredField("secret");
        secret.setAccessible(true);
        secret.set(jwtUtil, "mysecretkeymysecretkeymysecretkey123");

        Field exp = JwtUtil.class.getDeclaredField("expiration");
        exp.setAccessible(true);
        exp.set(jwtUtil, 1000000L);
    }

    @Test
    void testGenerateAndValidateToken() {
        String token = jwtUtil.generateToken("test@mail.com", "OWNER");

        assertNotNull(token);
        assertEquals("test@mail.com", jwtUtil.extractEmail(token));
        assertEquals("OWNER", jwtUtil.extractRole(token));
        assertTrue(jwtUtil.validateToken(token, "test@mail.com"));
    }
}