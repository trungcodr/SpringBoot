package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.PrescriptionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetails, Long> {
    List<PrescriptionDetails> findByPrescriptionId(Long prescriptionId);
}
