package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO dto) {
        log.info("Adding user");
        return userService.addUser(dto);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/staff")
    public UserDTO addStaff(@RequestBody UserDTO dto) {
        return userService.addStaff(dto);
    }

    @GetMapping("/staff")
    public List<UserDTO> getAllStaff() {
        return userService.getAllStaff();
    }

    @PutMapping("/staff/{id}")
    public UserDTO updateStaff(@PathVariable int id, @RequestBody UserDTO dto) {
        return userService.updateStaff(id, dto);
    }

    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable int id) {
        userService.deleteStaff(id);
        return "Staff deleted successfully";
    }
}