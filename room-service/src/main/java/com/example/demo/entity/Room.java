package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

        
    
    @NotBlank(message = "Room type is required")
    private String type; // DELUXE / AC / NON-AC

    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Availability is required")
    private Boolean available;;

    public Room() {}

    public Room(String type, double price, boolean available) {
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getId() { return id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
