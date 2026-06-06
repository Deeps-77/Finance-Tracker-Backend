package com.example.financetracker.repository;

import com.example.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query that automatically filters records matching the logged-in user's username
    List<Transaction> findByUserUsername(String username);
}