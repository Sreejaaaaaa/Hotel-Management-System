package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.feign.BookingClient;
import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private BookingClient bookingClient;

    @GetMapping("/income")
    public double getIncome() {
        return service.getTotalIncome();
    }

    @GetMapping("/bookings")
    public Object getBookings() {
        return bookingClient.getAllBookings();
    }
}