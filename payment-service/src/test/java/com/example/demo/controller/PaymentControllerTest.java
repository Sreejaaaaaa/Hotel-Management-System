//package com.example.demo.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import com.example.demo.dto.PaymentDTO;
//import com.example.demo.entity.Payment;
//import com.example.demo.service.PaymentService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(PaymentController.class)
//class PaymentControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PaymentService paymentService;   // ✅ CORRECT
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    // ✅ MAKE PAYMENT
//    @Test
//    void testMakePayment() throws Exception {
//
//        PaymentDTO dto = new PaymentDTO();
//        dto.setBookingId(1);
//        dto.setAmount(1000);
//
//        Payment saved = new Payment();
//        saved.setBookingId(1);
//        saved.setAmount(1000);
//        saved.setStatus("SUCCESS");
//        saved.setTransactionId("TXN123");
//
//        when(paymentService.makePayment(Mockito.any())).thenReturn(saved);
//
//        mockMvc.perform(post("/payments")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(dto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("SUCCESS"))
//                .andExpect(jsonPath("$.transactionId").value("TXN123"));
//    }
//
//    // ✅ VALIDATION FAIL
//    @Test
//    void testMakePaymentValidationFail() throws Exception {
//
//        PaymentDTO dto = new PaymentDTO();
//        dto.setBookingId(0);
//        dto.setAmount(-10);
//
//        mockMvc.perform(post("/payments")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(dto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    // ✅ GET ALL
//    @Test
//    void testGetAllPayments() throws Exception {
//
//        Payment p = new Payment();
//        p.setBookingId(1);
//        p.setAmount(1000);
//        p.setStatus("SUCCESS");
//
//        when(paymentService.getAllPayments()).thenReturn(List.of(p));
//
//        mockMvc.perform(get("/payments"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].bookingId").value(1));
//    }
//
//    // ✅ FILTER BY BOOKING
//    @Test
//    void testGetPaymentsByBooking() throws Exception {
//
//        Payment p = new Payment();
//        p.setBookingId(1);
//        p.setAmount(1000);
//        p.setStatus("SUCCESS");
//
//        when(paymentService.getPaymentsByBooking(1))
//                .thenReturn(List.of(p));
//
//        mockMvc.perform(get("/payments/booking/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].bookingId").value(1));
//    }
//
//    // ✅ EMPTY LIST
//    @Test
//    void testGetPaymentsByBookingEmpty() throws Exception {
//
//        when(paymentService.getPaymentsByBooking(1))
//                .thenReturn(List.of());
//
//        mockMvc.perform(get("/payments/booking/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[]"));
//    }
//
//    // ✅ VERIFY PAYMENT
//    @Test
//    void testVerifyPayment() throws Exception {
//
//        when(paymentService.verifyPayment()).thenReturn("VERIFIED");
//
//        mockMvc.perform(post("/payments/verify"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("VERIFIED"));
//    }
//}