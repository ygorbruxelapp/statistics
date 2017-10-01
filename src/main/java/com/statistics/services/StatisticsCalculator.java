package com.statistics.services;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsCalculator {

    private static final int SCALE = 2;

    public Statistics calculate(List<Transaction> all) {
        DoubleSummaryStatistics summaryStatistics = calculateStatistics(all);

        BigDecimal preciseSum = calculatePreciseSum(all);

        double preciseAverage = calculatePreciseAverage(summaryStatistics.getCount(), preciseSum);

        return new Statistics(
                preciseSum.doubleValue(),
                preciseAverage,
                getMaxOrZeroDefault(summaryStatistics.getMax()),
                getMinOrZeroDefault(summaryStatistics.getMin()),
                summaryStatistics.getCount());
    }

    private DoubleSummaryStatistics calculateStatistics(List<Transaction> all) {
        return all
                .stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();
    }

    private double calculatePreciseAverage(long count, BigDecimal preciseSum) {
        if (count == 0) {
            return 0;
        }
        BigDecimal countAsBigDecimal = BigDecimal.valueOf(count);
        return preciseSum
                .divide(countAsBigDecimal, SCALE, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private BigDecimal calculatePreciseSum(List<Transaction> all) {
        return all
                .stream()
                .map((t) -> BigDecimal.valueOf(t.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private double getMaxOrZeroDefault(double max) {
        return max == Double.NEGATIVE_INFINITY ? 0d : max;
    }

    private double getMinOrZeroDefault(double min) {
        return min == Double.POSITIVE_INFINITY ? 0d : min;
    }
}
