package com.example.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.inventory.entity.Inventory;
import com.example.inventory.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testAdd() throws Exception {
        Inventory item = new Inventory();
        item.setId(1);
        item.setItemName("TV");

        when(service.addItem(Mockito.any())).thenReturn(item);

        mockMvc.perform(post("/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("TV"));
    }

    @Test
    void testGetAll() throws Exception {
        Inventory item = new Inventory();
        item.setId(1);
        item.setItemName("TV");

        when(service.getAllItems()).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("TV"));
    }

    @Test
    void testUpdate() throws Exception {
        Inventory item = new Inventory();
        item.setId(1);
        item.setItemName("Updated");

        when(service.updateItem(Mockito.eq(1), Mockito.any())).thenReturn(item);

        mockMvc.perform(put("/inventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Updated"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}