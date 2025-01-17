package com.example.clubmanager_ws.service;

import com.example.clubmanager_ws.dto.BookingInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BookingService {
    ResponseEntity<?> createBooking(Map<String, Object> payload);
    ResponseEntity<Object> searchBookings(String memberName, String startDate, String endDate);
}

