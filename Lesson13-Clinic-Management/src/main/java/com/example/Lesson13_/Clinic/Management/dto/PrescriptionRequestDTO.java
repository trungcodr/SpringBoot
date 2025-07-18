package com.example.Lesson13_.Clinic.Management.dto;

import java.util.List;

public class PrescriptionRequestDTO {
    private Long medicalFormId;

    private List<MedicineInPrescriptionDTO > medicines;

    public Long getMedicalFormId() {
        return medicalFormId;
    }

    public void setMedicalFormId(Long medicalFormId) {
        this.medicalFormId = medicalFormId;
    }

    public List<MedicineInPrescriptionDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineInPrescriptionDTO> medicines) {
        this.medicines = medicines;
    }
}
