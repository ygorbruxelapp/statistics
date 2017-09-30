package com.statistics.services;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsCalculator {
    public Statistics calculate(List<Transaction> all) {
        DoubleSummaryStatistics summaryStatistics = all
                .stream()
                .filter(t -> !t.isExpired())
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        return new Statistics(
                summaryStatistics.getSum(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getCount());
    }
}
