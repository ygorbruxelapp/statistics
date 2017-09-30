package com.statistics.domain;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Transaction {

    private double amount;
    private long timestamp;

    public boolean isExpired() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime sixtySecondsAgo = utc.minusSeconds(60);

        return timestamp < (sixtySecondsAgo.toEpochSecond() * 1000);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
