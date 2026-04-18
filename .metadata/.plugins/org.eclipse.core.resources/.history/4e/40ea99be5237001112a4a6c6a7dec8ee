package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository repo;

    @Test
    void testSaveAndFind() {

        Payment p = new Payment(1, 1000, "SUCCESS");

        Payment saved = repo.save(p);

        assertNotNull(saved.getId());
        assertFalse(repo.findAll().isEmpty());
    }
}