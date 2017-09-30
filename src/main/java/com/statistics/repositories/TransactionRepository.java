package com.statistics.repositories;

import com.statistics.domain.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    final List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = Collections.synchronizedList(new ArrayList<Transaction>());
    }

    public void create(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> findAll() {
        ArrayList<Transaction> transactionsClone;
        synchronized (this.transactions) {
            transactionsClone = new ArrayList<>(transactions);
        }
        return transactionsClone;
    }
}
