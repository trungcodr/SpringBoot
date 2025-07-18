package com.example.Lesson13_.Clinic.Management.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "medical_form_service")
public class MedicalFormServiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicalFormId;
    private Long serviceId;
    private String serviceName;
    private BigDecimal servicePrice;
    private String dosage;
    private String instruction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicalFormId() {
        return medicalFormId;
    }

    public void setMedicalFormId(Long medicalFormId) {
        this.medicalFormId = medicalFormId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
