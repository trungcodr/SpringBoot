package com.example.Lesson13_.Clinic.Management.mapper;

import com.example.Lesson13_.Clinic.Management.dto.PatientRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.PatientResponseDTO;
import com.example.Lesson13_.Clinic.Management.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientMapper {
    public Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setDob(dto.getDob());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        return patient;
    }

    public PatientRequestDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientRequestDTO patientDTO = new PatientRequestDTO();
        patientDTO.setName(patient.getName());
        patientDTO.setGender(patient.getGender());
        patientDTO.setDob(patient.getDob());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setAddress(patient.getAddress());
        return patientDTO;
    }

    public PatientResponseDTO toResponseDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setGender(patient.getGender());
        patientResponseDTO.setDob(patient.getDob());
        patientResponseDTO.setPhone(patient.getPhone());
        patientResponseDTO.setAddress(patient.getAddress());
        return patientResponseDTO;
    }

    public List<PatientResponseDTO> toResponseDTOList(List<Patient> patients) {
        List<PatientResponseDTO> dtoList = new ArrayList<>();
        for (Patient patient : patients) {
            dtoList.add(toResponseDTO(patient));
        }
        return dtoList;
    }

    public void update(Patient patient, PatientRequestDTO dto) {
        if (patient == null || dto == null) {
            return;
        }
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setDob(dto.getDob());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());

    }
}
