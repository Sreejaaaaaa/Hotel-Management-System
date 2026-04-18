package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
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

    @MockBean
    private UserRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    // ✅ ADD USER
    @Test
    void testAddUser() throws Exception {

        User u = new User("A", "a@mail.com");

        when(repo.save(any())).thenReturn(u);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(mapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }

    // ✅ GET ALL USERS
    @Test
    void testGetUsers() throws Exception {

        when(repo.findAll()).thenReturn(List.of(new User("A", "a@mail.com")));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    // ✅ GET USER BY ID (FOUND)
    @Test
    void testGetUserByIdFound() throws Exception {

        when(repo.findById(1)).thenReturn(Optional.of(new User("A", "a@mail.com")));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    // ✅ GET USER BY ID (NOT FOUND)
    @Test
    void testGetUserByIdNotFound() throws Exception {

        when(repo.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    // ✅ GET USER BY EMAIL (FOUND)
    @Test
    void testGetUserByEmailFound() throws Exception {

        User u = new User("A", "a@mail.com");

        when(repo.findByEmail("a@mail.com")).thenReturn(u);

        mockMvc.perform(get("/users/email/a@mail.com"))
                .andExpect(status().isOk());
    }

    // ✅ GET USER BY EMAIL (NOT FOUND)
    @Test
    void testGetUserByEmailNotFound() throws Exception {

        when(repo.findByEmail("x@mail.com")).thenReturn(null);

        mockMvc.perform(get("/users/email/x@mail.com"))
                .andExpect(status().isNotFound());
    }

    // ✅ ADD STAFF (VALID ROLE)
    @Test
    void testAddStaffValid() throws Exception {

        User u = new User();
        u.setRole("OWNER");

        when(repo.save(any())).thenReturn(u);

        mockMvc.perform(post("/users/staff")
                .contentType("application/json")
                .content(mapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }

    // ✅ ADD STAFF (INVALID ROLE)
    @Test
    void testAddStaffInvalid() throws Exception {

        User u = new User();
        u.setRole("INVALID");

        mockMvc.perform(post("/users/staff")
                .contentType("application/json")
                .content(mapper.writeValueAsString(u)))
                .andExpect(status().isBadRequest()); // ✅ FIXED
    }

    // ✅ GET ALL STAFF (FILTER LOGIC)
    @Test
    void testGetAllStaff() throws Exception {

        User u1 = new User();
        u1.setRole("OWNER");

        User u2 = new User();
        u2.setRole("USER");

        when(repo.findAll()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/users/staff"))
                .andExpect(status().isOk());
    }

    // ✅ UPDATE STAFF (SUCCESS)
    @Test
    void testUpdateStaffSuccess() throws Exception {

        User existing = new User();
        existing.setRole("OWNER");

        when(repo.findById(1)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(existing);

        mockMvc.perform(put("/users/staff/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(existing)))
                .andExpect(status().isOk());
    }

    // ✅ UPDATE STAFF (NOT FOUND)
    @Test
    void testUpdateStaffNotFound() throws Exception {

        when(repo.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(put("/users/staff/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(new User())))
                .andExpect(status().isBadRequest()); // ✅ FIXED
    }

    // ✅ DELETE STAFF
    @Test
    void testDeleteStaff() throws Exception {

        doNothing().when(repo).deleteById(1);

        mockMvc.perform(delete("/users/staff/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Staff deleted successfully"));
    }
}