package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository repo;

    @InjectMocks
    private DepartmentService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartment() {

        Department dept = new Department();
        dept.setName("IT");

        when(repo.save(dept)).thenReturn(dept);

        Department result = service.addDepartment(dept);

        assertEquals("IT", result.getName());
        verify(repo).save(dept);
    }

    @Test
    void testGetAllDepartments() {

        when(repo.findAll()).thenReturn(List.of(new Department(), new Department()));

        List<Department> list = service.getAllDepartments();

        assertEquals(2, list.size());
        verify(repo).findAll();
    }

    @Test
    void testUpdateDepartment() {

        Department dept = new Department();
        dept.setName("HR");

        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));

        Department result = service.updateDepartment(1, dept);

        assertEquals(1, result.getId());
        verify(repo).save(dept);
    }

    @Test
    void testDeleteDepartment() {

        service.deleteDepartment(1);

        verify(repo).deleteById(1);
    }
}