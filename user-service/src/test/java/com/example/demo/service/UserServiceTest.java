package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        User u = new User();
        u.setName("John");

        when(repo.findAll()).thenReturn(List.of(u));

        List<UserDTO> result = service.getUsers();

        assertEquals(1, result.size());
    }

    @Test
    void testGetUserById() {
        User u = new User();
        u.setId(1);

        when(repo.findById(1)).thenReturn(java.util.Optional.of(u));

        UserDTO result = service.getUserById(1);

        assertNotNull(result);
    }
}