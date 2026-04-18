package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingClient {

    @GetMapping("/booking/all")
    Object getAllBookings();
}