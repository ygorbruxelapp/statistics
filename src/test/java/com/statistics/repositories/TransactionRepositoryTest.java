package com.statistics.repositories;

import com.statistics.domain.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionRepositoryTest {

    @Test
    public void addAndFindTransactionsInTheRepository() {
        TransactionRepository transactionRepository = new TransactionRepository();

        Transaction transaction = new Transaction();
        transaction.setTimestamp(123);
        transactionRepository.create(transaction);

        assertEquals(123L, transactionRepository.findAll().get(0).getTimestamp());
    }
}
