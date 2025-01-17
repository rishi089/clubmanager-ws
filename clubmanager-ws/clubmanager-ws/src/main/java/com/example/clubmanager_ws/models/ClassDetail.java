package com.example.clubmanager_ws.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassDetail {
    private static AtomicInteger idCounter = new AtomicInteger();
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int duration;
    private int capacity;

    public ClassDetail(String name, LocalDate startDate, LocalDate endDate, LocalTime startTime, int duration, int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be at least 1.");
        if (endDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("End date must be in the future.");
        this.id = idCounter.incrementAndGet();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.duration = duration;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalTime getStartTime() { return startTime; }
    public int getDuration() { return duration; }
    public int getCapacity() { return capacity; }
}

