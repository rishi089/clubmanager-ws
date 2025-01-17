package com.example.clubmanager_ws.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingInfo {
    private String memberName;
    private String className;
    private LocalTime classStartTime;
    private LocalDate bookingDate;

    public BookingInfo(String memberName, String className, LocalTime classStartTime, LocalDate bookingDate) {
        this.memberName = memberName;
        this.className = className;
        this.classStartTime = classStartTime;
        this.bookingDate = bookingDate;
    }

    public String getMemberName() { return memberName; }
    public String getClassName() { return className; }
    public LocalTime getClassStartTime() { return classStartTime; }
    public LocalDate getBookingDate() { return bookingDate; }
}
