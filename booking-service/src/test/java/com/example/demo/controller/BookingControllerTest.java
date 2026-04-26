package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BookingService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService service;

    @MockBean
    private BookingRepository bookingRepo;

    @MockBean
    private GuestRepository guestRepo;

    @Test
    void testGetAllBookings() throws Exception {

        Booking b = new Booking();
        b.setId(1);
        b.setGuestId(1);   // 🔥 VERY IMPORTANT
        b.setRoomId(101);
        b.setStatus("CONFIRMED");

        when(bookingRepo.findAll()).thenReturn(List.of(b));

        mockMvc.perform(get("/booking/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBooking() throws Exception {

        mockMvc.perform(delete("/booking/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateBooking() throws Exception {

        when(service.createBooking(any()))
                .thenReturn(CompletableFuture.completedFuture(Map.of()));

        mockMvc.perform(post("/booking")
                .contentType("application/json")
                .content("{\"guest\":{},\"booking\":{}}"))
                .andExpect(status().isOk());
    }
    @Test
    void testAddGuest() throws Exception {

        Guest g = new Guest();
        g.setId(1);
        g.setName("John");

        when(guestRepo.save(any())).thenReturn(g);

        mockMvc.perform(post("/booking/guest")
                .contentType("application/json")
                .content("""
                {
                  "name": "John",
                  "email": "john@mail.com"
                }
                """))
                .andExpect(status().isOk());
    }
}