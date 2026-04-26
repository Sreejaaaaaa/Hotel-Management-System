package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String successMethod() {
        return "OK";
    }

    public void failMethod() {
        throw new RuntimeException("Failure occurred");
    }
}