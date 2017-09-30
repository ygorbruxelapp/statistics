package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.services.StatisticsCalculator;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static com.statistics.factory.TransactionFactory.getCurrentTransactionWithAmount;
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

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(140d, statistics.getSum());
    }

    @Test
    public void calculateAverageOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(70d, statistics.getAvg());
    }

    @Test
    public void setsMaxAmountOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(100d, statistics.getMax());
    }

    @Test
    public void maxAmoountDefaultValueShouldBeZero() {
        Statistics statistics = calculate();
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setsMinAmountOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(40d, statistics.getMin());
    }

    @Test
    public void minAmoountDefaultValueShouldBeZero() {
        Statistics statistics = calculate();
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setTotalNumberOfTransactions() {
        Transaction bigTransaction = getCurrentTransactionWithAmount(100);
        Transaction smallTransaction = getCurrentTransactionWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(2, statistics.getCount());
    }

    private Statistics calculate(Transaction... transactions) {
        return statisticsCalculator.calculate(
                Arrays.asList(transactions),
                ZonedDateTime.now(ZoneOffset.UTC));
    }
}
