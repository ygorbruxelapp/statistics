package com.statistics.domain;

import java.util.DoubleSummaryStatistics;
import java.util.List;

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
