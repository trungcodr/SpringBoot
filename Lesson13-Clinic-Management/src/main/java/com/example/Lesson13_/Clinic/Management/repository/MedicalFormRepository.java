package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.MedicalForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFormRepository extends JpaRepository<MedicalForm, Long> {
    boolean existsById(Long id);

}
