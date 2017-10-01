package com.statistics.repositories;

import com.statistics.domain.Transaction;
import com.statistics.factory.TransactionFactory;
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
        Transaction transaction = TransactionFactory.getCurrentWithAmount(123);
        transactionRepository.create(transaction);

        assertEquals(1, transactionRepository.findAll().size());
    }

    @Test
    public void removeTransactions() {
        Transaction transaction = TransactionFactory.getCurrentWithAmount(123);
        transactionRepository.create(transaction);
        transactionRepository.delete(transaction);

        assertEquals(0, transactionRepository.findAll().size());
    }
}
