package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public Department addDepartment(Department dept) {
        return repo.save(dept);
    }

    public List<Department> getAllDepartments() {
        return repo.findAll();
    }

    public Department updateDepartment(int id, Department dept) {
        dept.setId(id);
        return repo.save(dept);
    }

    public void deleteDepartment(int id) {
        repo.deleteById(id);
    }
}