package com.statistics.domain;


import org.junit.Test;

import static com.statistics.factory.TimestampFactory.getTimestampsSecondsAgo;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class TransactionTest {

    @Test
    public void transactionIsNotExpiredFrom59SecondsAgo() {
        Transaction transaction = buildTransactionWithSecondsAgo(59);
        assertFalse(transaction.isExpired());
    }

    @Test
    public void transactionIsExpiredFrom61SecondsAgo() {
        Transaction transaction = buildTransactionWithSecondsAgo(61);
        assertTrue(transaction.isExpired());
    }

    private Transaction buildTransactionWithSecondsAgo(int seconds) {
        long timestamp = getTimestampsSecondsAgo(seconds);

        Transaction transaction = new Transaction();
        transaction.setTimestamp(timestamp);
        return transaction;
    }
}
