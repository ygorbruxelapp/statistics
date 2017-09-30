package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.services.StatisticsCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;


public class StatisticsCalculatorTest {

    private StatisticsCalculator statisticsCalculator;

    @Before
    public void setUp() {
        statisticsCalculator = new StatisticsCalculator();
    }

    @Test
    public void calculateSumOfTransactions() {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(100);

        Transaction smallTransaction = new Transaction();
        smallTransaction.setAmount(10.50);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(110.50, statistics.getSum());
    }

    @Test
    public void calculateAverageOfTransactions() {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(100);

        Transaction smallTransaction = new Transaction();
        smallTransaction.setAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(70d, statistics.getAvg());
    }

    @Test
    public void setsMaxAmountOfTransactions() {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(100);

        Transaction smallTransaction = new Transaction();
        smallTransaction.setAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(100d, statistics.getMax());
    }

    @Test
    public void setsMinAmountOfTransactions() {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(100);

        Transaction smallTransaction = new Transaction();
        smallTransaction.setAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(40d, statistics.getMin());
    }

    @Test
    public void setTotalNumberOfTransactions() {
        Transaction bigTransaction = new Transaction();
        bigTransaction.setAmount(100);

        Transaction smallTransaction = new Transaction();
        smallTransaction.setAmount(40);

        Statistics statistics = statisticsCalculator.calculate(Arrays.asList(bigTransaction, smallTransaction));

        assertEquals(2, statistics.getCount());
    }
}
