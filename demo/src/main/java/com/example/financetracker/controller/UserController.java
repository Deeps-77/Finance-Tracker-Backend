package com.example.financetracker.controller;

import com.example.financetracker.model.User;
import com.example.financetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll(){
        return service.getAllUsers();
    }

    @PostMapping("/register")
    public User createuser(@RequestBody User user){
        return service.createUser(user);
    }
}
