package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testGettersSetters() {

        User u = new User();
        u.setId(1);
        u.setName("Kiran");
        u.setEmail("test@mail.com");
        u.setPassword("123");
        u.setRole("OWNER");

        assertEquals(1, u.getId());
        assertEquals("Kiran", u.getName());
        assertEquals("test@mail.com", u.getEmail());
        assertEquals("123", u.getPassword());
        assertEquals("OWNER", u.getRole());
    }

    @Test
    void testConstructor() {
        User u = new User("John", "john@mail.com");

        assertEquals("John", u.getName());
        assertEquals("john@mail.com", u.getEmail());
    }
}