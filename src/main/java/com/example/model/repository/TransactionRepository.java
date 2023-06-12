package com.example.model.repository;

import com.example.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction , Integer> {

    List<Transaction> findAllByCustomerid(Long customerId);
}
