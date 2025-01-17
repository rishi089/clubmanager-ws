package com.example.clubmanager_ws.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        String memberName = "Ram";
        int classId = 1;
        LocalDate participationDate = LocalDate.of(2024, 12, 5);
        Booking booking = new Booking(memberName, classId, participationDate);

        assertEquals(memberName, booking.getMemberName());
        assertEquals(classId, booking.getClassId());
        assertEquals(participationDate, booking.getParticipationDate());
    }

    @Test
    public void testBookingWithNullMemberName() {
        String memberName = null;
        int classId = 1;
        LocalDate participationDate = LocalDate.of(2024, 12, 5);
        Booking booking = new Booking(memberName, classId, participationDate);

        assertNull(booking.getMemberName());
        assertEquals(classId, booking.getClassId());
        assertEquals(participationDate, booking.getParticipationDate());
    }
}

