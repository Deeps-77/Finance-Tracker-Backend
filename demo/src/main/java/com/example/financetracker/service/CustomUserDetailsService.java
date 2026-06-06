package com.example.financetracker.service;

// Assuming your user model is named UserEntity
import com.example.financetracker.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Look up the user in your database
        com.example.financetracker.model.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Return a Spring Security User object containing the username and encrypted password
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) // Note: This password MUST be encrypted in the database
                .build();
    }
}