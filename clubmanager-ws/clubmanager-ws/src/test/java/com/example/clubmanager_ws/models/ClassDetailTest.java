package com.example.clubmanager_ws.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class ClassDetailTest {

    @Test
    public void testClassDetailWithInvalidCapacity() {
        String name = "Yoga Class";
        LocalDate startDate = LocalDate.of(2024, Month.FEBRUARY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.MARCH, 1);
        LocalTime startTime = LocalTime.of(10, 0);
        int duration = 60;
        int capacity = 0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClassDetail(name, startDate, endDate, startTime, duration, capacity);
        });
        assertEquals("Capacity must be at least 1.", exception.getMessage());
    }

    @Test
    public void testClassDetailWithEndDateInThePast() {
        String name = "Yoga Class";
        LocalDate startDate = LocalDate.of(2024, Month.FEBRUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 1);
        LocalTime startTime = LocalTime.of(10, 0);
        int duration = 60;
        int capacity = 25;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClassDetail(name, startDate, endDate, startTime, duration, capacity);
        });
        assertEquals("End date must be in the future.", exception.getMessage());
    }
}
