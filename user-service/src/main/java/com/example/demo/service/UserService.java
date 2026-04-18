package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // DTO → Entity
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    // Entity → DTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    // ADD USER
    public UserDTO addUser(UserDTO dto) {
        User saved = userRepository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    // GET ALL USERS
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }

    // GET BY EMAIL
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? convertToDTO(user) : null;
    }

    // ADD STAFF
    public UserDTO addStaff(UserDTO dto) {
        if (dto.getRole() == null ||
                (!dto.getRole().equals("OWNER") &&
                 !dto.getRole().equals("MANAGER") &&
                 !dto.getRole().equals("RECEPTIONIST"))) {

            throw new RuntimeException("Invalid staff role");
        }

        User saved = userRepository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    // GET STAFF
    public List<UserDTO> getAllStaff() {
        List<String> roles = List.of("OWNER", "MANAGER", "RECEPTIONIST");

        return userRepository.findAll()
                .stream()
                .filter(user -> roles.contains(user.getRole()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE STAFF
    public UserDTO updateStaff(int id, UserDTO dto) {
        User existing = userRepository.findById(id).orElse(null);

        if (existing == null) {
            throw new RuntimeException("Staff not found");
        }

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPassword(dto.getPassword());
        existing.setRole(dto.getRole());

        return convertToDTO(userRepository.save(existing));
    }

    // DELETE STAFF
    public void deleteStaff(int id) {
        userRepository.deleteById(id);
    }
}