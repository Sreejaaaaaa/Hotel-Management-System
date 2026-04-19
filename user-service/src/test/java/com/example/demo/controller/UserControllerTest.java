package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ✅ FIX: mock service instead of repository
    @MockBean
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    // ✅ ADD USER
    @Test
    void testAddUser() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setName("A");
        dto.setEmail("a@mail.com");

        when(userService.addUser(any())).thenReturn(dto);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    // ✅ GET USERS
    @Test
    void testGetUsers() throws Exception {

        when(userService.getUsers()).thenReturn(List.of(new UserDTO()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    // ✅ GET BY ID
    @Test
    void testGetUserById() throws Exception {

        when(userService.getUserById(1)).thenReturn(new UserDTO());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    // ✅ EMAIL FOUND
    @Test
    void testGetUserByEmailFound() throws Exception {

        when(userService.getUserByEmail("a@mail.com"))
                .thenReturn(new UserDTO());

        mockMvc.perform(get("/users/email/a@mail.com"))
                .andExpect(status().isOk());
    }

    // ✅ EMAIL NOT FOUND
    @Test
    void testGetUserByEmailNotFound() throws Exception {

        when(userService.getUserByEmail("x@mail.com"))
                .thenReturn(null);

        mockMvc.perform(get("/users/email/x@mail.com"))
                .andExpect(status().isNotFound());
    }

    // ✅ ADD STAFF
    @Test
    void testAddStaff() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setRole("OWNER");

        when(userService.addStaff(any())).thenReturn(dto);

        mockMvc.perform(post("/users/staff")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    // ✅ GET STAFF
    @Test
    void testGetAllStaff() throws Exception {

        when(userService.getAllStaff()).thenReturn(List.of(new UserDTO()));

        mockMvc.perform(get("/users/staff"))
                .andExpect(status().isOk());
    }

    // ✅ UPDATE STAFF
    @Test
    void testUpdateStaff() throws Exception {

        UserDTO dto = new UserDTO();

        when(userService.updateStaff(eq(1), any())).thenReturn(dto);

        mockMvc.perform(put("/users/staff/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    // ✅ DELETE STAFF
    @Test
    void testDeleteStaff() throws Exception {

        doNothing().when(userService).deleteStaff(1);

        mockMvc.perform(delete("/users/staff/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Staff deleted successfully"));
    }
}