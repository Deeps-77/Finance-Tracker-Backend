package com.example.financetracker.service;

import com.example.financetracker.model.User;
import com.example.financetracker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

        public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User createUser(User user) {
        String password = user.getPassword();
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
