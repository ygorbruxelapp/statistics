package com.statistics.domain;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Transaction {

    private double amount;
    private long timestamp;

    public Transaction() {
        amount = 0;
        timestamp = 0;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isExpired() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime sixtySecondsAgo = utc.minusSeconds(60);

        return timestamp < (sixtySecondsAgo.toEpochSecond() * 1000);
    }
}
