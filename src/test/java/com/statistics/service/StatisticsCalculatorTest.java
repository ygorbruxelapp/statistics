package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.factory.TransactionFactory;
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
        Transaction bigTransaction = TransactionFactory.getCurrentWithAmount(100);
        Transaction smallTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(140d, statistics.getSum());
    }

    @Test
    public void calculateSumAccuratelly() {
        Transaction aTransaction = TransactionFactory.getCurrentWithAmount(12.3);
        Transaction anotherTransaction = TransactionFactory.getCurrentWithAmount(12.3);
        Transaction thirdTransaction = TransactionFactory.getCurrentWithAmount(12.3);

        Statistics statistics = calculate(aTransaction, anotherTransaction, thirdTransaction);

        assertEquals(36.9d, statistics.getSum());
    }

    @Test
    public void calculateAverageOfTransactions() {
        Transaction bigTransaction = TransactionFactory.getCurrentWithAmount(100);
        Transaction smallTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(70d, statistics.getAvg());
    }

    @Test
    public void calculateAverageAccuratelly() {
        Transaction aTransaction = TransactionFactory.getCurrentWithAmount(20);
        Transaction anotherTransaction = TransactionFactory.getCurrentWithAmount(40);
        Transaction thirdTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(aTransaction, anotherTransaction, thirdTransaction);

        assertEquals(33.33d, statistics.getAvg());
    }

    @Test
    public void setsMaxAmountOfTransactions() {
        Transaction bigTransaction = TransactionFactory.getCurrentWithAmount(100);
        Transaction smallTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(100d, statistics.getMax());
    }

    @Test
    public void maxAmountDefaultValueShouldBeZero() {
        Statistics statistics = calculate();
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setsMinAmountOfTransactions() {
        Transaction bigTransaction = TransactionFactory.getCurrentWithAmount(100);
        Transaction smallTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(40d, statistics.getMin());
    }

    @Test
    public void minAmountDefaultValueShouldBeZero() {
        Statistics statistics = calculate();
        assertEquals(0d, statistics.getMax());
    }

    @Test
    public void setTotalNumberOfTransactions() {
        Transaction bigTransaction = TransactionFactory.getCurrentWithAmount(100);
        Transaction smallTransaction = TransactionFactory.getCurrentWithAmount(40);

        Statistics statistics = calculate(bigTransaction, smallTransaction);

        assertEquals(2, statistics.getCount());
    }

    private Statistics calculate(Transaction... transactions) {
        return statisticsCalculator.calculate(
                Arrays.asList(transactions));
    }
}
