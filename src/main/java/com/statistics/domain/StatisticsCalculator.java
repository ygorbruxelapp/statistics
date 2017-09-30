package com.statistics.domain;

import java.util.List;

public class StatisticsCalculator {
    public Statistics calculate(List<Transaction> all) {
        double sum = all.stream().mapToDouble(Transaction::getAmount).sum();
        double avg = all.stream().mapToDouble(Transaction::getAmount).average().getAsDouble();
        double max = all.stream().mapToDouble(Transaction::getAmount).max().getAsDouble();
        double min = all.stream().mapToDouble(Transaction::getAmount).min().getAsDouble();
        long count = all.size();

        Statistics statistics = new Statistics();
        statistics.setSum(sum);
        statistics.setAvg(avg);
        statistics.setMax(max);
        statistics.setMin(min);
        statistics.setCount(count);

        return statistics;
    }
}
