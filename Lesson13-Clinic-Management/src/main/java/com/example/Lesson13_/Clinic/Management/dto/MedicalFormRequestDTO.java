package com.example.Lesson13_.Clinic.Management.dto;

import java.time.LocalDate;
import java.util.List;

public class MedicalFormRequestDTO {
    private String doctorName;
    private String patientName;
    private String gender;
    private LocalDate dateOfBirth;
    private List<ServiceInFormDTO> services;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<ServiceInFormDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceInFormDTO> services) {
        this.services = services;
    }
}
