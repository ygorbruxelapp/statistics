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
                getMaxOrZeroDefault(summaryStatistics.getMax()),
                getMinOrZeroDefault(summaryStatistics.getMin()),
                summaryStatistics.getCount());
    }

    private double getMaxOrZeroDefault(double max) {
        return max == Double.NEGATIVE_INFINITY ? 0d : max;
    }

    private double getMinOrZeroDefault(double min) {
        return min == Double.POSITIVE_INFINITY ? 0d : min;
    }
}
