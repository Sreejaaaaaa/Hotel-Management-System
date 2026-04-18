package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;

@FeignClient(name = "USER-SERVICE")   
public interface UserClient {

    @GetMapping("/users/email/{email}")   
    User getUserByEmail(@PathVariable("email") String email);
}