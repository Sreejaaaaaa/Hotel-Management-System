package com.example.demo.model;

public class Payment {

    private int id;
    private int bookingId;
    private double amount;
    private String status;

    public int getId() { return id; }

    public int getBookingId() { return bookingId; }

    public double getAmount() { return amount; }

    public String getStatus() { return status; }
}