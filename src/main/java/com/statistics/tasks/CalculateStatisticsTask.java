package com.statistics.tasks;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.repositories.StatisticsRepository;
import com.statistics.repositories.TransactionRepository;
import com.statistics.services.StatisticsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateStatisticsTask {

    private TransactionRepository transactionRepository;

    private StatisticsRepository statisticsRepository;

    private StatisticsCalculator statisticsCalculator;

    @Autowired
    public CalculateStatisticsTask(StatisticsCalculator statisticsCalculator, TransactionRepository transactionRepository, StatisticsRepository statisticsRepository) {
        this.statisticsCalculator = statisticsCalculator;
        this.transactionRepository = transactionRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Scheduled(fixedRate = 300)
    public void updateStatistics() {
        List<Transaction> all = transactionRepository.findAll();
        Statistics statistics = statisticsCalculator.calculate(all);
        statisticsRepository.set(statistics);
    }
}
