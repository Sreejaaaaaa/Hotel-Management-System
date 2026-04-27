package com.example.demo.service;

import java.util.HashMap;
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
    

    @Async
    public CompletableFuture<Map<String, Object>> createBooking(BookingRequest request) {

        // Save guest
        Guest savedGuest = guestRepository.save(request.getGuest());

        // Prepare booking
        Booking booking = request.getBooking();
        booking.setGuestId(savedGuest.getId());

        // Check room
        Room room = roomClient.getRoomById(booking.getRoomId());

        if (room == null || !room.isAvailable()) {
            throw new RuntimeException("Room not available");
        }

        // Update room availability
        room.setAvailable(false);
        roomClient.updateRoom(room.getId(), room);

        // Save booking
        booking.setStatus("PENDING");
        Booking saved = bookingRepository.save(booking);

        // ✅ ONLY PAYMENT CALL (Billing handled inside Payment)
//        Map<String, Object> paymentRequest = new HashMap<>();
//        paymentRequest.put("bookingId", saved.getId());
//        paymentRequest.put("amount", room.getPrice());
//
//        paymentClient.makePayment(paymentRequest);

        // Final response
        Map<String, Object> response = new HashMap<>();
        response.put("booking", saved);
        response.put("guest", savedGuest);

        return CompletableFuture.completedFuture(response);
    }
    
    public Booking cancelBooking(int bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ✅ update status
        booking.setStatus("CANCELLED");

        // ✅ FREE the room
        Room room = roomClient.getRoomById(booking.getRoomId());

        if (room != null) {
            room.setAvailable(true);
            roomClient.updateRoom(room.getId(), room);
        }

        return bookingRepository.save(booking);
    }
}