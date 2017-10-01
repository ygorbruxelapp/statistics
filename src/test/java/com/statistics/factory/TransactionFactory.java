package com.statistics.factory;

import com.statistics.domain.Transaction;

import java.util.UUID;

import static com.statistics.factory.TimestampFactory.getTimestampsSecondsAgo;

public class TransactionFactory {
    public static Transaction buildTransactionWithSecondsAgo(int seconds) {
        long timestamp = getTimestampsSecondsAgo(seconds);

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    public static Transaction getCurrentWithAmount(double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(getTimestampsSecondsAgo(10));
        return transaction;
    }
}
