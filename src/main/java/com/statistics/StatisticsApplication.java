package com.statistics;

import com.statistics.repositories.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StatisticsApplication {

    @Bean
    public TransactionRepository transactionRepository() {
        return new TransactionRepository();
    }

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
