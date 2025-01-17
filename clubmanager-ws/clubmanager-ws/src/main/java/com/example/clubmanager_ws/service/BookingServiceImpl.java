package com.example.clubmanager_ws.service;

import com.example.clubmanager_ws.dto.BookingInfo;
import com.example.clubmanager_ws.models.Booking;
import com.example.clubmanager_ws.models.ClassDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//Created this for handling business logic for booking classes and search bookings
@Service
public class BookingServiceImpl implements BookingService {

    private List<Booking> bookings = new ArrayList<>();
    private final ClubClassService classService;

    @Autowired
    public BookingServiceImpl(ClubClassService classService) {
        this.classService = classService;
    }

    @Override
    public ResponseEntity<?> createBooking(Map<String, Object> payload) {
        String memberName = (String) payload.get("memberName");
        int classId = (int) payload.get("classId");
        LocalDate participationDate = LocalDate.parse((String) payload.get("participationDate"));

        Optional<ClassDetail> classOpt = classService.getClassById(classId);
        if (classOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found.");
        }

        ClassDetail classDetail = classOpt.get();
        if (participationDate.isBefore(classDetail.getStartDate()) || participationDate.isAfter(classDetail.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Participation date is out of class schedule.");
        }

        long bookingCount = bookings.stream()
                .filter(b -> b.getClassId() == classId)
                .count();

        if (bookingCount >= classDetail.getCapacity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Class capacity exceeded.");
        }

        bookings.add(new Booking(memberName, classId, participationDate));
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
    }

    @Override
    public ResponseEntity<Object> searchBookings(String memberName, String startDate, String endDate) {
        try {
            LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
            LocalDate end = endDate != null ? LocalDate.parse(endDate) : null;

            List<BookingInfo> result = bookings.stream()
                    .filter(b -> (memberName == null || b.getMemberName().equalsIgnoreCase(memberName)) &&
                            (start == null || !b.getParticipationDate().isBefore(start)) &&
                            (end == null || !b.getParticipationDate().isAfter(end)))
                    .map(b -> {
                        Optional<ClassDetail> classDetailOpt = classService.getClassById(b.getClassId());
                        if (classDetailOpt.isEmpty()) return null;

                        ClassDetail classDetail = classDetailOpt.get();
                        return new BookingInfo(
                                b.getMemberName(),
                                classDetail.getName(),
                                classDetail.getStartTime(),
                                b.getParticipationDate()
                        );
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

