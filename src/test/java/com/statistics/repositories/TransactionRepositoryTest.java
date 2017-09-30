package com.statistics.repositories;

import com.statistics.domain.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionRepositoryTest {

    TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    public void addTransactions() {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(123);
        transactionRepository.create(transaction);

        assertEquals(123L, transactionRepository.findAll().get(0).getTimestamp());
    }

    @Test
    public void removeTransactions() {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(123);
        transactionRepository.create(transaction);
        transactionRepository.delete(transaction);

        assertEquals(0, transactionRepository.findAll().size());
    }
}
