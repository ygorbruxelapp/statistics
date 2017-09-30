package com.statistics.tasks;

import com.statistics.domain.Statistics;
import com.statistics.services.StatisticsCalculator;
import com.statistics.domain.Transaction;
import com.statistics.repositories.StatisticsRepository;
import com.statistics.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculateStatisticsTaskTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private StatisticsCalculator statisticsCalculator;

    @Test
    public void testTaskFlow() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.findAll()).thenReturn(transactions);

        Statistics statistics = new Statistics();
        when(statisticsCalculator.calculate(transactions)).thenReturn(statistics);

        CalculateStatisticsTask calculateStatisticsTask = new CalculateStatisticsTask(statisticsCalculator, transactionRepository, statisticsRepository);
        calculateStatisticsTask.updateStatistics();

        verify(transactionRepository, times(1)).findAll();
        verify(statisticsRepository, times(1)).set(statistics);
    }
}
