package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.PatientRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.PatientResponseDTO;
import com.example.Lesson13_.Clinic.Management.entity.Patient;
import com.example.Lesson13_.Clinic.Management.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientRequestDTO requestDTO) {
        Patient save = patientService.createPatient(requestDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatient(@PathVariable Long id) {
        PatientResponseDTO dto = patientService.findPatientById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> dtos = patientService.getAllPatients();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,
                                                            @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO dto = patientService.updatePatient(id, requestDTO);
        return ResponseEntity.ok(dto);

    }
}
