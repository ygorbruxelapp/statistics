package com.statistics.domain;


import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.statistics.factory.TransactionFactory.buildTransactionWithSecondsAgo;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TransactionTest {

    @Test
    public void transactionIsNotExpiredFrom59SecondsAgo() {
        Transaction transaction = buildTransactionWithSecondsAgo(59);
        assertFalse(transaction.isExpired(ZonedDateTime.now(ZoneOffset.UTC)));
    }

    @Test
    public void transactionIsExpiredFrom61SecondsAgo() {
        Transaction transaction = buildTransactionWithSecondsAgo(61);
        assertTrue(transaction.isExpired(ZonedDateTime.now(ZoneOffset.UTC)));
    }

}
