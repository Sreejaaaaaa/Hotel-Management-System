package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddDepartment() throws Exception {

        Department dept = new Department();
        dept.setName("IT");

        when(service.addDepartment(any())).thenReturn(dept);

        mockMvc.perform(post("/departments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllDepartments() throws Exception {

        Department d = new Department();
        d.setId(1);
        d.setName("IT");

        when(service.getAllDepartments()).thenReturn(List.of(d));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateDepartment() throws Exception {

        Department dept = new Department();
        dept.setName("HR");

        when(service.updateDepartment(eq(1), any())).thenReturn(dept);

        mockMvc.perform(put("/departments/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteDepartment() throws Exception {

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk());
    }
}