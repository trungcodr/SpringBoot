package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientById(Long id);
    boolean existsByPhone(String phone);
}
