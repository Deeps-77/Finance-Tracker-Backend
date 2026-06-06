package com.example.financetracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter             // Automatically creates getters for ALL fields (including id)
@Setter             // Automatically creates setters for ALL fields // CRUCIAL: Creates the empty constructor Jackson needs to parse JSON
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User() {}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
