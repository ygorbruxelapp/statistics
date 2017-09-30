package com.statistics.factory;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TimestampFactory {
    public static long getTimestampsSecondsAgo(int seconds) {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime zonedDateTime = utc.minusSeconds(seconds);
        return zonedDateTime.toEpochSecond() * 1000;
    }
}
