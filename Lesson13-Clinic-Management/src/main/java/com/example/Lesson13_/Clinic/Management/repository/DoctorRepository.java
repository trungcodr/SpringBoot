package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findDoctorById(Long id);
}
