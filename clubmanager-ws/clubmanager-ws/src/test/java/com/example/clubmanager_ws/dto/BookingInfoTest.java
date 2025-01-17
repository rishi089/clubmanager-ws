package com.example.clubmanager_ws.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class BookingInfoTest {

    @Test
    public void testBookingInfoCreation() {
        String memberName = "Ram";
        String className = "Yoga Class";
        LocalTime classStartTime = LocalTime.of(10, 0);
        LocalDate bookingDate = LocalDate.of(2024, Month.FEBRUARY, 1);

        BookingInfo bookingInfo = new BookingInfo(memberName, className, classStartTime, bookingDate);

        assertEquals(memberName, bookingInfo.getMemberName());
        assertEquals(className, bookingInfo.getClassName());
        assertEquals(classStartTime, bookingInfo.getClassStartTime());
        assertEquals(bookingDate, bookingInfo.getBookingDate());
    }

    @Test
    public void testBookingInfoWithNullMemberName() {
        String memberName = null;
        String className = "Yoga Class";
        LocalTime classStartTime = LocalTime.of(10, 0);
        LocalDate bookingDate = LocalDate.of(2024, Month.FEBRUARY, 1);

        BookingInfo bookingInfo = new BookingInfo(memberName, className, classStartTime, bookingDate);

        assertNull(bookingInfo.getMemberName());
        assertEquals(className, bookingInfo.getClassName());
        assertEquals(classStartTime, bookingInfo.getClassStartTime());
        assertEquals(bookingDate, bookingInfo.getBookingDate());
    }
}

