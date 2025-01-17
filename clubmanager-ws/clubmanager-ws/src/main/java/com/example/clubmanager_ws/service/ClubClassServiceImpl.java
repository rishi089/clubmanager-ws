package com.example.clubmanager_ws.service;

import com.example.clubmanager_ws.models.ClassDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Created this for handling business logic for creating classes
@Service
public class ClubClassServiceImpl implements ClubClassService {

    private final List<ClassDetail> classes = new ArrayList<>();

    @Override
    public ResponseEntity<?> createClass(Map<String, Object> payload) {
        String name = (String) payload.get("name");
        LocalDate startDate = LocalDate.parse((String) payload.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) payload.get("endDate"));
        LocalTime startTime = LocalTime.parse((String) payload.get("startTime"));
        int duration = (int) payload.get("duration");
        int capacity = (int) payload.get("capacity");

        if (endDate.isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be in the future.");
        }

        if (capacity < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Capacity must be at least 1.");
        }

        ClassDetail newClass = new ClassDetail(name, startDate, endDate, startTime, duration, capacity);
        classes.add(newClass);

        return ResponseEntity.status(HttpStatus.CREATED).body(newClass);
    }

    @Override
    public Optional<ClassDetail> getClassById(int id) {
        return classes.stream().filter(c -> c.getId() == id).findFirst();
    }
}

