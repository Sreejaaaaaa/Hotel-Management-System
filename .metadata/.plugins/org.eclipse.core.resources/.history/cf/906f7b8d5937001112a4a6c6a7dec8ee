package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testGettersSetters() throws Exception {

        Room r = new Room();
        r.setType("AC");
        r.setPrice(2000);
        r.setAvailable(true);

        // cover ID
        java.lang.reflect.Field f = Room.class.getDeclaredField("id");
        f.setAccessible(true);
        f.set(r, 1);

        assertEquals(1, r.getId());
        assertEquals("AC", r.getType());
        assertEquals(2000, r.getPrice());
        assertTrue(r.isAvailable());
    }

    @Test
    void testConstructor() {
        Room r = new Room("DELUXE", 3000, true);

        assertEquals("DELUXE", r.getType());
        assertEquals(3000, r.getPrice());
        assertTrue(r.isAvailable());
    }
}