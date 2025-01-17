package com.example.clubmanager_ws.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.clubmanager_ws.models.ClassDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public class ClubClassServiceImplTest {

    @InjectMocks
    private ClubClassServiceImpl clubClassService;


    @BeforeEach
    public void setUp() {
        clubClassService = new ClubClassServiceImpl();
    }

    @Test
    public void testCreateClass_Success() {
        Map<String, Object> payload = Map.of(
                "name", "Yoga Class",
                "startDate", "2025-05-01",
                "endDate", "2025-05-10",
                "startTime", "10:00",
                "duration", 60,
                "capacity", 20
        );

        ResponseEntity<?> response = clubClassService.createClass(payload);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ClassDetail createdClass = (ClassDetail) response.getBody();
        assertNotNull(createdClass);
        assertEquals("Yoga Class", createdClass.getName());
        assertEquals(20, createdClass.getCapacity());
    }

    @Test
    public void testCreateClass_EndDateInPast() {
        Map<String, Object> payload = Map.of(
                "name", "Yoga Class",
                "startDate", "2025-05-01",
                "endDate", "2023-06-01",
                "startTime", "10:00",
                "duration", 60,
                "capacity", 20
        );

        ResponseEntity<?> response = clubClassService.createClass(payload);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("End date must be in the future.", response.getBody());
    }

    @Test
    public void testCreateClass_InvalidCapacity() {
        Map<String, Object> payload = Map.of(
                "name", "Yoga Class",
                "startDate", "2025-05-01",
                "endDate", "2025-06-01",
                "startTime", "10:00",
                "duration", 60,
                "capacity", 0
        );

        ResponseEntity<?> response = clubClassService.createClass(payload);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Capacity must be at least 1.", response.getBody());
    }

    @Test
    public void testGetClassById_Found() {
        clubClassService.createClass(Map.of(
                "name", "Yoga Class",
                "startDate", "2025-05-01",
                "endDate", "2025-06-01",
                "startTime", "10:00",
                "duration", 60,
                "capacity", 20
        ));

        Optional<ClassDetail> result = clubClassService.getClassById(1);

        assertTrue(result.isPresent());
        assertEquals("Yoga Class", result.get().getName());
    }

    @Test
    public void testGetClassById_NotFound() {
        Optional<ClassDetail> result = clubClassService.getClassById(999);

        assertFalse(result.isPresent());
    }
}

