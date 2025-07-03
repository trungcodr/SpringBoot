package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.PatientRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.PatientResponseDTO;
import com.example.Lesson13_.Clinic.Management.entity.Patient;
import com.example.Lesson13_.Clinic.Management.mapper.PatientMapper;
import com.example.Lesson13_.Clinic.Management.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    // Them moi benh nhan
    public Patient createPatient(PatientRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new IllegalArgumentException("Ten benh nhan khong duoc de trong!");
        }
        Patient patient = patientMapper.toEntity(requestDTO);
        return patientRepository.save(patient);
    }

    // Xem thong tin benh nhan
    public PatientResponseDTO findPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new IllegalArgumentException("Khong tim thay benh nhan voi id: " + id);
        }
        return patientMapper.toResponseDTO(patient);
    }

    // Xem danh sach benh nhan
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toResponseDTOList(patients);
    }

    // Cap nhat thong tin benh nhan
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new IllegalArgumentException("Khong tim thay benh nhan voi id: " + id);
        }
        patientMapper.update(patient, requestDTO);
        Patient updated = patientRepository.save(patient);
        return patientMapper.toResponseDTO(updated);
    }
}
