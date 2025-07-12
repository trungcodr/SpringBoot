package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findAll();

    @Query(value = """
            select diagnosis
            from MedicalRecord
            where date between :start and :end
            group by diagnosis
            order by count (*) desc
            limit 1
    """, nativeQuery = true)
    String findMostDiagnosis(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
