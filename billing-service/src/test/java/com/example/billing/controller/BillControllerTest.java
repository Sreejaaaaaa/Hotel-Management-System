package com.example.billing.controller;

import com.example.billing.dto.BillDTO;
import com.example.billing.entity.Bill;
import com.example.billing.service.BillService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BillController.class)
class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @Test
    void testGenerateBill() throws Exception {

        // Mock service response
        Bill bill = new Bill();
        bill.setBookingId(1L);
        bill.setAmount(100.0);
        bill.setTax(18.0);
        bill.setTotalAmount(118.0);

        when(billService.generateBill(any(Bill.class))).thenReturn(bill);

        String requestJson = """
                {
                    "bookingId": 1,
                    "amount": 100.0
                }
                """;

        mockMvc.perform(post("/bill/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(1))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.tax").value(18.0))
                .andExpect(jsonPath("$.totalAmount").value(118.0));
    }
}