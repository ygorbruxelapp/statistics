package com.statistics.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {

    private double amount;
    private long timestamp;
    private UUID id;

    public boolean isExpired(ZonedDateTime utc) {
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
