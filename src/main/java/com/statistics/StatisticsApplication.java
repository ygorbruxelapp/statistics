package com.statistics;

import com.statistics.repositories.StatisticsRepository;
import com.statistics.repositories.TransactionRepository;
import com.statistics.services.StatisticsCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class StatisticsApplication {

    @Bean
    public TransactionRepository transactionRepository() {
        return new TransactionRepository();
    }

    @Bean
    public StatisticsRepository statisticsRepository() {
        return new StatisticsRepository();
    }

    @Bean
    public StatisticsCalculator statisticsCalculator() {
        return new StatisticsCalculator();
    }

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
