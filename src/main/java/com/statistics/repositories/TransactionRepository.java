package com.statistics.repositories;

import com.statistics.domain.Transaction;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TransactionRepository {

    final List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = Collections.synchronizedList(new ArrayList<Transaction>());
    }

    @Async
    public void create(Transaction transaction) {
        transaction.setId(UUID.randomUUID());
        transactions.add(transaction);
    }

    public List<Transaction> findAll() {
        ArrayList<Transaction> transactionsClone;
        synchronized (this.transactions) {
            transactionsClone = new ArrayList<>(transactions);
        }
        return transactionsClone;
    }

    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }
}
