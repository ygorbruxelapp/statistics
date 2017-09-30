package com.statistics.tasks;

import com.statistics.domain.Transaction;
import com.statistics.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static com.statistics.factory.TransactionFactory.buildTransactionWithSecondsAgo;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class RemoveExpiredTransactionsTaskTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testDeletesExpiredTasks() {
        Transaction notExpiredTransaction = buildTransactionWithSecondsAgo(50);
        Transaction expiredTransaction = buildTransactionWithSecondsAgo(70);
        List<Transaction> transactions = Arrays.asList(expiredTransaction, notExpiredTransaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        RemoveExpiredTransactionsTask removeExpiredTransactionsTask = new RemoveExpiredTransactionsTask(transactionRepository);
        removeExpiredTransactionsTask.updateStatistics();

        verify(transactionRepository, times(1)).findAll();
        verify(transactionRepository, times(1)).delete(expiredTransaction);
    }
}
