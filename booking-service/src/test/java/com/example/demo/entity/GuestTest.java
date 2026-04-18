package com.example.demo.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void testGettersSetters() {

        Guest g = new Guest();
        g.setId(1);
        g.setName("Test");
        g.setEmail("a@mail.com");
        g.setPhone("123");
        g.setAddress("addr");
        g.setGender("M");

        assertEquals("Test", g.getName());
        assertEquals("a@mail.com", g.getEmail());
    }
}