package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Status is required")
    private String status;    // SUCCESS / FAILED

    public Payment() {}

    public Payment(int bookingId, double amount, String status) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
    }

    public int getId() { return id; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}