package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping
    public Department add(@RequestBody Department dept) {
        return service.addDepartment(dept);
    }

    @GetMapping
    public List<Department> getAll() {
        return service.getAllDepartments();
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable int id, @RequestBody Department dept) {
        return service.updateDepartment(id, dept);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteDepartment(id);
        return "Deleted Successfully";
    }
}