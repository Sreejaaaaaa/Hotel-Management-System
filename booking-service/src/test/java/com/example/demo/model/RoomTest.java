package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoom() {

        Room r = new Room();
        r.setId(1);
        r.setType("AC");
        r.setPrice(100);
        r.setAvailable(true);

        assertTrue(r.isAvailable());
    }
}