package com.statistics.domain;

public class Transaction {

    private final double amount;
    private final long timestamp;

    public Transaction() {
        amount = 0;
        timestamp = 0;
    }

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
