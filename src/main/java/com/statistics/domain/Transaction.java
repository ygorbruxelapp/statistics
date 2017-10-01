package com.statistics.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {

    private static final int SECONDS_TO_EXPIRE = 60;
    public static final int TO_MILLISECONDS = 1000;
    private double amount;
    private long timestamp;
    private UUID id;

    public boolean isExpired(ZonedDateTime utc) {
        ZonedDateTime sixtySecondsAgo = utc.minusSeconds(SECONDS_TO_EXPIRE);

        return timestamp < (sixtySecondsAgo.toEpochSecond() * TO_MILLISECONDS);
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
