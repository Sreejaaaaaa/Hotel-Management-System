package com.example.demo.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Room;
import com.example.demo.dto.RoomDTO;
import com.example.demo.service.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private static final Logger log =
            LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService service;

    // Add Room
    @PostMapping
    public RoomDTO addRoom(@Valid @RequestBody RoomDTO dto) {

        Room room = new Room();
        room.setType(dto.getType());
        room.setPrice(dto.getPrice());
        room.setAvailable(true);

        Room saved = service.addRoom(room);

        RoomDTO response = new RoomDTO();
        response.setId(saved.getId());
        response.setType(saved.getType());
        response.setPrice(saved.getPrice());
        response.setAvailable(saved.isAvailable());

        return response;
    }

    // Get All Rooms
    @GetMapping
    public List<RoomDTO> getRooms() {

        return service.getAllRooms()
                .stream()
                .map(room -> {
                    RoomDTO dto = new RoomDTO();
                    dto.setId(room.getId());
                    dto.setType(room.getType());
                    dto.setPrice(room.getPrice());
                    dto.setAvailable(room.isAvailable());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Get Room by ID (FIXED)
    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable int id) {

        Room room = service.getRoomById(id); // already throws exception if not found

        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setType(room.getType());
        dto.setPrice(room.getPrice());
        dto.setAvailable(room.isAvailable());

        return dto;
    }

    // Get Available Rooms
    @GetMapping("/available")
    public List<RoomDTO> getAvailableRooms() {

        return service.getAvailableRooms()
                .stream()
                .map(room -> {
                    RoomDTO dto = new RoomDTO();
                    dto.setId(room.getId());
                    dto.setType(room.getType());
                    dto.setPrice(room.getPrice());
                    dto.setAvailable(room.isAvailable());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Search Rooms
    @GetMapping("/search")
    public List<RoomDTO> searchRooms() {

        return service.getAvailableRooms()
                .stream()
                .map(room -> {
                    RoomDTO dto = new RoomDTO();
                    dto.setId(room.getId());
                    dto.setType(room.getType());
                    dto.setPrice(room.getPrice());
                    dto.setAvailable(room.isAvailable());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Update Room
    @PutMapping("/{id}")
    public RoomDTO updateRoom(@PathVariable int id, @RequestBody RoomDTO dto) {

        Room room = new Room();
        room.setType(dto.getType());
        room.setPrice(dto.getPrice());
        room.setAvailable(dto.isAvailable());

        Room updated = service.updateRoom(id, room);

        RoomDTO response = new RoomDTO();
        response.setId(updated.getId());
        response.setType(updated.getType());
        response.setPrice(updated.getPrice());
        response.setAvailable(updated.isAvailable());

        return response;
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable int id) {

        service.deleteRoom(id);

        return "Room deleted successfully";
    }
}