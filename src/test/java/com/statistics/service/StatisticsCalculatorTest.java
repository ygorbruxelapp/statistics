package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.services.StatisticsCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.statistics.factory.TimestampFactory.getTimestampsSecondsAgo;
import static junit.framework.TestCase.assertEquals;


public class StatisticsCalculatorTest {

    private StatisticsCalculator statisticsCalculator;

    @Before
    public void setUp() {
        statisticsCalculator = new StatisticsCalculator();
    }

    @Test
    public void calculateSumOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(140d, statistics.getSum());
    }

    @Test
    public void calculateAverageOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(70d, statistics.getAvg());
    }

    @Test
    public void setsMaxAmountOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(100d, statistics.getMax());
    }

    @Test
    public void maxAmoountDefaultValueShouldBeZero() {
        Statistics statistics = statisticsCalculator.calculate(Arrays.asList());
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setsMinAmountOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(40d, statistics.getMin());
    }

    @Test
    public void minAmoountDefaultValueShouldBeZero() {
        Statistics statistics = statisticsCalculator.calculate(Arrays.asList());
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setTotalNumberOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(2, statistics.getCount());
    }

    @Test
    public void removeOldTransactionsWhenCalculateStatistics() {
        Transaction expiredTransaction = new Transaction();
        expiredTransaction.setTimestamp(getTimestampsSecondsAgo(100));

        Transaction notExpiredTransaction = getCurrentTransactionWithAmount(22.2);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(expiredTransaction, notExpiredTransaction));

        assertEquals(1, statistics.getCount());
        assertEquals(22.2, statistics.getSum());
    }

    private Transaction getCurrentTransactionWithAmount(double amount) {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(amount);
        bigTransaction.setTimestamp(getTimestampsSecondsAgo(10));
        return bigTransaction;
    }
}
