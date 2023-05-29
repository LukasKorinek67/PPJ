package com.korinek.MeteorologicalDataApp.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Timestamp {


    public static long getNow() {
        Instant now = Instant.now();
        return now.getEpochSecond();
    }
    public static long timeStampMinusDay(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        Instant minusDay = instant.minus(1, ChronoUnit.DAYS);
        return minusDay.getEpochSecond();
    }

    public static long timeStampMinusWeek(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        Instant minusWeek = instant.minus(7, ChronoUnit.DAYS);
        return minusWeek.getEpochSecond();
    }

    public static long timeStampMinus14Days(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        Instant minus14days = instant.minus(14, ChronoUnit.DAYS);
        return minus14days.getEpochSecond();
    }
}
