package com.example.Lesson13_.Clinic.Management.repository;
import com.example.Lesson13_.Clinic.Management.entity.MedicalFormServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicalFormServiceRepository extends JpaRepository<MedicalFormServiceDetails, Long> {
}
