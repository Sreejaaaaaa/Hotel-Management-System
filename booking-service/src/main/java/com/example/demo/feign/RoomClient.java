package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Room;

@FeignClient(name = "ROOM-SERVICE")
public interface RoomClient {

    @GetMapping("/rooms/{id}")
    Room getRoomById(@PathVariable int id);

    @PutMapping("/rooms/{id}")
    Room updateRoom(@PathVariable int id, @RequestBody Room room);
}