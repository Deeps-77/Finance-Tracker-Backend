package com.example.financetracker.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.persistence.*;






@Setter
@Getter
@Entity
public class Transaction {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double amount;
    private String type; // "INCOME" or "EXPENSE"

    // LINK TO THE USER: Creates a 'user_id' foreign key column in PostgreSQL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Prevents infinite recursion during JSON serialization
    private User user;

    public Transaction() {}

    public Transaction(String description, double amount, String type, User user) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.user = user;
    }

}