package com.example.clubmanager_ws.controller;

import com.example.clubmanager_ws.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Bookings Management", description = "APIs for managing class bookings and search bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint to create a new booking.
     */
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> payload) {
        return bookingService.createBooking(payload);
    }

    /**
     * Endpoint to search bookings with filters.
     */
    @GetMapping
    public ResponseEntity<Object> searchBookings(
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return bookingService.searchBookings(memberName, startDate, endDate);
    }
}
