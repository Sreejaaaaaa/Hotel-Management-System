package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        log.info("Login attempt for user: {}", request.getEmail());

        String token = service.login(request);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setMessage("Login successful");

        log.info("Token generated for user: {}", request.getEmail());

        return response;
    }
}