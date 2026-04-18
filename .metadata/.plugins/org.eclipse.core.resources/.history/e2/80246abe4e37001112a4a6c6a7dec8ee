package com.example.demo.model;

import com.example.demo.entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingRequestTest {

    @Test
    void testGettersSetters() {

        BookingRequest r = new BookingRequest();

        Guest g = new Guest();
        Booking b = new Booking();

        r.setGuest(g);
        r.setBooking(b);

        assertEquals(g, r.getGuest());
        assertEquals(b, r.getBooking());
    }
}