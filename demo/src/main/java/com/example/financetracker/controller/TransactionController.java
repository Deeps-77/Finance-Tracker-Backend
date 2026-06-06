package com.example.financetracker.controller;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAll(Principal principal) {
        // principal.getName() extracts the validated username from the Basic Auth header
        return service.getAllTransactions(principal.getName());
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction, Principal principal) {
        return service.createTransaction(transaction, principal.getName());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        service.deleteTransaction(id, principal.getName());
        return "Transaction with ID " + id + " deleted successfully.";
    }
}