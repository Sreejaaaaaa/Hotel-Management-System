package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.demo.feign.BookingClient;
import com.example.demo.service.ReportService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService service;

    @MockBean
    private BookingClient bookingClient;

    @Test
    void testGetIncome() throws Exception {

        when(service.getTotalIncome()).thenReturn(500.0);

        mockMvc.perform(get("/reports/income"))
                .andExpect(status().isOk())
                .andExpect(content().string("500.0"));
    }

    @Test
    void testGetBookings() throws Exception {

        when(bookingClient.getAllBookings()).thenReturn("DATA");

        mockMvc.perform(get("/reports/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().string("DATA"));
    }
}