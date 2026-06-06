package com.example.financetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication(exclude = {
        ContextFunctionCatalogAutoConfiguration.class
})
public class FinanceTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceTrackerApplication.class, args);
    }

    // Add this configuration bean to manually satisfy Spring AI Ollama
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}