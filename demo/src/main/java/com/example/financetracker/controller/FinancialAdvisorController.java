package com.example.financetracker.controller;

import com.example.financetracker.service.FinancialAdvisorService;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/advisor")
@CrossOrigin(origins = "http://localhost:5173")
public class FinancialAdvisorController {

    private final FinancialAdvisorService advisorService;

    public FinancialAdvisorController(FinancialAdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @PostMapping("/chat")
    public ChatResponse askAdvisor(@RequestBody ChatRequest request, Principal principal) {
        // 1. Grab the secure username from the Spring Security Principal
        String username = principal.getName();

        // 2. Pass the raw user message and the secure username to the service layer.
        // The service layer handles database fetching to ensure maximum security.
        String advice = advisorService.analyzeFinances(request.getMessage(), username);

        return new ChatResponse(advice);
    }

    // Lean and secure request payload payload mapping
    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Clean response wrapper matching the React frontend json expectations
    public static class ChatResponse {
        private final String response;

        public ChatResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }
}