package com.example.demo.model;


import com.example.demo.entity.Booking;
import com.example.demo.entity.Guest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class BookingRequest {

	@Valid
    @NotNull(message = "Guest details required")
    private Guest guest;

    @Valid
    @NotNull(message = "Booking details required")
    private Booking booking;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
