package com.example.clubmanager_ws.models;

import java.time.LocalDate;

public class Booking {
    private String memberName;
    private int classId;
    private LocalDate participationDate;

    public Booking(String memberName, int classId, LocalDate participationDate) {
        this.memberName = memberName;
        this.classId = classId;
        this.participationDate = participationDate;
    }

    public String getMemberName() { return memberName; }
    public int getClassId() { return classId; }
    public LocalDate getParticipationDate() { return participationDate; }
}

