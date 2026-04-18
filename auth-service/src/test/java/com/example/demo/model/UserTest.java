package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGettersAndSetters() {

        User user = new User();

        user.setId(1L);
        user.setName("Kiran");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setRole("OWNER");

        assertEquals(1L, user.getId());
        assertEquals("Kiran", user.getName());
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("123", user.getPassword());
        assertEquals("OWNER", user.getRole());
    }
}