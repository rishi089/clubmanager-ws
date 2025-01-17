package com.example.clubmanager_ws.controller;

import com.example.clubmanager_ws.models.ClassDetail;
import com.example.clubmanager_ws.service.ClubClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Class Management", description = "APIs for managing class details")
class ClubClassController {
    private final List<ClassDetail> classes = new ArrayList<>();

    private final ClubClassService classService;

    @Autowired
    public ClubClassController(ClubClassService classService) {
        this.classService = classService;
    }

    /**
     * Endpoint to create classes.
     */
    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody Map<String, Object> payload) {
        return classService.createClass(payload);
    }

    /**
     * Endpoint to get class details from provided id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getClassById(@PathVariable int id) {
        Optional<ClassDetail> classOpt = classService.getClassById(id);
        return classOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found."));
    }
}
