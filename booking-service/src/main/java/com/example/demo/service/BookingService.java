package com.example.demo.service;

import java.util.HashMap;
import com.example.demo.feign.BillingClient;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Guest;
import com.example.demo.feign.PaymentClient;
import com.example.demo.feign.RoomClient;
import com.example.demo.model.BookingRequest;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.GuestRepository;

@Service
public class BookingService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private PaymentClient paymentClient; 
    
    @Autowired
    private BillingClient billingClient;

    @Async
    public CompletableFuture<Map<String, Object>> createBooking(BookingRequest request) {

        Guest savedGuest = guestRepository.save(request.getGuest());

        Booking booking = request.getBooking();
        booking.setGuestId(savedGuest.getId());

        Room room = roomClient.getRoomById(booking.getRoomId());

        if (room == null || !room.isAvailable()) {
            throw new RuntimeException("Room not available");
        }

        room.setAvailable(false);
        roomClient.updateRoom(room.getId(), room);

        booking.setStatus("CONFIRMED");
        Booking saved = bookingRepository.save(booking);

        // Payment
        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("bookingId", saved.getId());
        paymentRequest.put("amount", room.getPrice());
        paymentClient.makePayment(paymentRequest);

        // Billing
        Map<String, Object> billRequest = new HashMap<>();
        billRequest.put("bookingId", saved.getId());
        billRequest.put("amount", room.getPrice());
        billRequest.put("tax", room.getPrice() * 0.1);
        billingClient.generateBill(billRequest);

        // ✅ FINAL RESPONSE
        Map<String, Object> response = new HashMap<>();
        response.put("booking", saved);
        response.put("guest", savedGuest);

        return CompletableFuture.completedFuture(response);
    }
}