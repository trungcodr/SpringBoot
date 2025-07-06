package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordResponseDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordWithDoctorDTO;
import com.example.Lesson13_.Clinic.Management.entity.MedicalRecord;
import com.example.Lesson13_.Clinic.Management.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-record")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecordRequestDTO requestDTO) {
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(requestDTO);
        return ResponseEntity.ok(medicalRecord);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getAllByPatientId(@PathVariable Long patientId) {
        List<MedicalRecordResponseDTO> dtoList = medicalRecordService.getAllMedicalRecordsById(patientId);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/with-doctor")
    public ResponseEntity<List<MedicalRecordWithDoctorDTO>> getAllWithDoctor() {
        return ResponseEntity.ok(medicalRecordService.getAllRecordsWithDoctorName());
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecordRequestDTO dto) {
        return ResponseEntity.ok(medicalRecordService.update(id, dto));
    }
}
