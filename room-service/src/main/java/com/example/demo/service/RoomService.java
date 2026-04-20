package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    // Add Room
    public Room addRoom(Room room) {
        return repo.save(room);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {
        return repo.findAll();
    }

    // Get Room by ID (FIXED)
    public Room getRoomById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // Get Available Rooms
    public List<Room> getAvailableRooms() {
        return repo.findAll()
                .stream()
                .filter(Room::isAvailable)
                .toList();
    }

    // Update Room (FIXED)
    public Room updateRoom(int id, Room room) {

        Room existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existing.setType(room.getType());
        existing.setPrice(room.getPrice());
        existing.setAvailable(room.isAvailable());

        return repo.save(existing);
    }

    // Delete Room (FIXED)
    public void deleteRoom(int id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Room not found");
        }

        repo.deleteById(id);
    }
}