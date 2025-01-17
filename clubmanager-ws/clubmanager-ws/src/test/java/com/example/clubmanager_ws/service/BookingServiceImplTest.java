package com.example.clubmanager_ws.service;

import com.example.clubmanager_ws.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceImplTest {

    @Mock
    private ClubClassService classService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private ClassDetail mockClassDetail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockClassDetail = new ClassDetail(
                "Yoga",
                LocalDate.of(2025, 3, 10),
                LocalDate.of(2025, 3, 20),
                LocalTime.of(9, 0),
                60,
                20
        );
    }


    @Test
    public void testCreateBookingSuccess() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("memberName", "Ram");
        payload.put("classId", 1);
        payload.put("participationDate", "2025-03-15");

        when(classService.getClassById(1)).thenReturn(Optional.of(mockClassDetail));

        ResponseEntity<?> response = bookingService.createBooking(payload);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Booking created successfully.", response.getBody());
    }

    @Test
    public void testCreateBookingClassNotFound() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("memberName", "Ram");
        payload.put("classId", 1);
        payload.put("participationDate", "2025-03-15");

        when(classService.getClassById(1)).thenReturn(Optional.empty());

        ResponseEntity<?> response = bookingService.createBooking(payload);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Class not found.", response.getBody());
    }

    @Test
    public void testCreateBookingParticipationDateOutOfSchedule() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("memberName", "Ram");
        payload.put("classId", 1);
        payload.put("participationDate", "2025-03-05");

        when(classService.getClassById(1)).thenReturn(Optional.of(mockClassDetail));

        ResponseEntity<?> response = bookingService.createBooking(payload);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Participation date is out of class schedule.", response.getBody());
    }

    @Test
    public void testSearchBookingsSuccess() {
        List<Booking> bookings = Arrays.asList(
                new Booking("Ram", 1, LocalDate.of(2025, 3, 15)),
                new Booking("Rahul", 1, LocalDate.of(2025, 3, 20))
        );

        when(classService.getClassById(1)).thenReturn(Optional.of(mockClassDetail));

        bookingService.setBookings(bookings);
        ResponseEntity<Object> response = bookingService.searchBookings("Ram", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    public void testSearchBookings_NoBookingsFound() {
        when(classService.getClassById(1)).thenReturn(Optional.of(mockClassDetail));

        ResponseEntity<Object> response = bookingService.searchBookings("Nonexist Member", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}

