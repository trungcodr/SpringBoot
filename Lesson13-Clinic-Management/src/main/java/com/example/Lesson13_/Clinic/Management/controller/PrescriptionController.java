package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.PrescriptionRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Prescription;
import com.example.Lesson13_.Clinic.Management.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionRequestDTO dto) {
        Prescription prescription = prescriptionService.createPrescription(dto);
        return ResponseEntity.ok(prescription);
    }


}
