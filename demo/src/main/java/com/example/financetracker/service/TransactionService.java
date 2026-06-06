package com.example.financetracker.service;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.TransactionRepository;
import com.example.financetracker.repository.UserRepository; // Assuming you have this
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // Filtered by username
    public List<Transaction> getAllTransactions(String username) {
        return repository.findByUserUsername(username);
    }

    // Binds the active user entity directly to the new transaction row before saving
    public Transaction createTransaction(Transaction transaction, String username) {
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        transaction.setUser(user);
        return repository.save(transaction);
    }

    // Security Check: Ensures users can only delete their own rows
    public void deleteTransaction(Long id, String username) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        if (!transaction.getUser().getUsername().equals(username)) {
            throw new SecurityException("Unauthorized action: This is not your ledger row!");
        }
        repository.deleteById(id);
    }
}