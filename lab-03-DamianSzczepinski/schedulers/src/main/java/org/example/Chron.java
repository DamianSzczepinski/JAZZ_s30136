package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {
    private LocalDateTime startTime;
    private LocalDateTime endDate;
    private int maxExecutionTimes;
    private int alreadyExecutedCount = 0;
    private LocalDateTime timeNow;
    private Duration intervalDuration;

    public static Chron builder()
    {
        return new Chron();
    }

    public Chron setStartTime(LocalDateTime startTime)
    {
        this.timeNow = startTime;
        this.startTime = startTime;
        return this;
    }

    public Chron setEndDate(LocalDateTime endDate)
    {
        this.endDate = endDate;
        return this;
    }

    public Chron setMaxExecutionTimes(int count)
    {
        this.maxExecutionTimes = count;
        return this;
    }

    public Chron setIntervalDuration(Duration interval)
    {
        this.intervalDuration = interval;
        return this;
    }

    public IProvideNextExecutionTime buildNextTimeExecutionProvider()
    {
        return () -> {
            if(alreadyExecutedCount >= maxExecutionTimes || (timeNow != null && timeNow.isEqual(endDate))  || (timeNow != null && timeNow.isAfter(endDate))) {
                return null;
            }

            if(timeNow != null)
                this.timeNow = timeNow.plus(intervalDuration);
            this.alreadyExecutedCount = alreadyExecutedCount+1;
            return timeNow;
        };
    }
}
