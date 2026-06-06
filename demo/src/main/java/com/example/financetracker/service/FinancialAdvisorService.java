package com.example.financetracker.service;

import com.example.financetracker.model.Transaction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialAdvisorService {

    private final ChatClient chatClient;
    private final TransactionService transactionService;

    public FinancialAdvisorService(ChatClient.Builder chatClientBuilder, TransactionService transactionService) {
        this.chatClient = chatClientBuilder.build();
        this.transactionService = transactionService;
    }

    // 1. Accept username as a parameter here
    public String analyzeFinances(String userPrompt, String username) {

        // 2. Pass the username here to isolate data for multi-user security
        List<Transaction> transactions = transactionService.getAllTransactions(username);

        String transactionContext = transactions.stream()
                .map(t -> String.format("- %s: $%s (%s)", t.getDescription(), t.getAmount(), t.getType()))
                .collect(Collectors.joining("\n"));

        String systemPrompt = """
                You are an expert personal financial advisor agent. 
                Below is the user's real-time transaction ledger data from their PostgreSQL database:
                
                [START TRANSACTION DATA]
                %s
                [END TRANSACTION DATA]
                
                Use ONLY this data to answer the user's question accurately. Be concise, professional, and highlight actionable spending habits. If no data exists, tell them to record transactions first.
                """.formatted(transactionContext);

        return chatClient.prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .call()
                .content();
    }
}