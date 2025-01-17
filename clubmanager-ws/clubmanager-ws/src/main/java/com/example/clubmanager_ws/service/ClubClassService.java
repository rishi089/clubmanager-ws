package com.example.clubmanager_ws.service;

import com.example.clubmanager_ws.models.ClassDetail;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public interface ClubClassService {
    ResponseEntity<?> createClass(Map<String, Object> payload);
    Optional<ClassDetail> getClassById(int id);
}

