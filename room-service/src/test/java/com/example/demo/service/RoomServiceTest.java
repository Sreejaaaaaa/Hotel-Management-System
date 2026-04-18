package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RoomServiceTest {

    @InjectMocks
    private RoomService service;

    @Mock
    private RoomRepository repo;

    private Room room;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        room = new Room("AC", 2000, true);
    }

    @Test
    void testAddRoom() {
        when(repo.save(room)).thenReturn(room);

        assertEquals(room, service.addRoom(room));
    }

    @Test
    void testGetAllRooms() {
        when(repo.findAll()).thenReturn(List.of(room));

        assertEquals(1, service.getAllRooms().size());
    }

    @Test
    void testGetRoomByIdFound() {
        when(repo.findById(1)).thenReturn(Optional.of(room));

        assertNotNull(service.getRoomById(1));
    }

    @Test
    void testGetRoomByIdNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertNull(service.getRoomById(1));
    }

    @Test
    void testGetAvailableRooms() {

        Room r2 = new Room("NON-AC", 1000, false);

        when(repo.findAll()).thenReturn(List.of(room, r2));

        List<Room> result = service.getAvailableRooms();

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateRoomSuccess() {

        when(repo.findById(1)).thenReturn(Optional.of(room));
        when(repo.save(any())).thenReturn(room);

        Room updated = new Room("DELUXE", 3000, false);

        Room result = service.updateRoom(1, updated);

        assertEquals("DELUXE", result.getType());
    }

    @Test
    void testUpdateRoomNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertNull(service.updateRoom(1, room));
    }

    @Test
    void testDeleteRoom() {
        doNothing().when(repo).deleteById(1);

        service.deleteRoom(1);

        verify(repo).deleteById(1);
    }
}