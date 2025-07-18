package com.example.Lesson13_.Clinic.Management.dto;

public class InvoiceRequestDTO {
    private Long medicalFormId;
    private String note;

    public Long getMedicalFormId() {
        return medicalFormId;
    }

    public void setMedicalFormId(Long medicalFormId) {
        this.medicalFormId = medicalFormId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
