package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.MedicalFormRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.MedicalForm;
import com.example.Lesson13_.Clinic.Management.service.MedicalFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-forms")
public class MedicalFormController {
    private final MedicalFormService medicalFormService;
    public MedicalFormController(MedicalFormService medicalFormService) {
        this.medicalFormService = medicalFormService;
    }

    //Tao moi phieu kham
    @PostMapping
    public ResponseEntity<MedicalForm> createMedicalForm(@RequestBody MedicalFormRequestDTO formRequestDTO) {
        MedicalForm medicalForm = medicalFormService.createMedicalForm(formRequestDTO);
        return ResponseEntity.ok().body(medicalForm);
    }


}
