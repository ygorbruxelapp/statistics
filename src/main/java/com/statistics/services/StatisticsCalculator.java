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
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        Statistics statistics = new Statistics();
        statistics.setSum(summaryStatistics.getSum());
        statistics.setAvg(summaryStatistics.getAverage());
        statistics.setMax(summaryStatistics.getMax());
        statistics.setMin(summaryStatistics.getMin());
        statistics.setCount(summaryStatistics.getCount());

        return statistics;
    }
}
