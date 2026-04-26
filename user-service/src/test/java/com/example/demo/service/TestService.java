package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String successMethod() {
        return "Success";
    }

    public void exceptionMethod() {
        throw new RuntimeException("Test Exception");
    }
}