package com.example.Lesson13_.Clinic.Management.dto;

import com.example.Lesson13_.Clinic.Management.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {
    private Long patientId;
    private LocalDateTime time;
    private String reason;
    private String note;
    private AppointmentStatus status;
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
