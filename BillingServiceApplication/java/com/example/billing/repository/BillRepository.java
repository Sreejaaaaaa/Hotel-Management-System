package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.billing.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}