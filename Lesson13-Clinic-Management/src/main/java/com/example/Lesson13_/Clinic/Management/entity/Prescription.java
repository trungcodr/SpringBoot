package com.example.Lesson13_.Clinic.Management.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicalFormId;
    private BigDecimal totalMedicinePrice;

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

    public BigDecimal getTotalMedicinePrice() {
        return totalMedicinePrice;
    }

    public void setTotalMedicinePrice(BigDecimal totalMedicinePrice) {
        this.totalMedicinePrice = totalMedicinePrice;
    }
}
