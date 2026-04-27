package com.example.demo.dto;

public class BookingDTO {

    private int bookingId;
    private int roomId;
    private String status;

    // 👇 NESTED OBJECT
    private GuestDTO guest;

    // getters & setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public GuestDTO getGuest() { return guest; }
    public void setGuest(GuestDTO guest) { this.guest = guest; }
}