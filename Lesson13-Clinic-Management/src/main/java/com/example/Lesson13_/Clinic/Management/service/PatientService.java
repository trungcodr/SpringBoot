package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.PatientRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.PatientResponseDTO;
import com.example.Lesson13_.Clinic.Management.dto.PatientSearchDTO;
import com.example.Lesson13_.Clinic.Management.entity.Patient;
import com.example.Lesson13_.Clinic.Management.mapper.PatientMapper;
import com.example.Lesson13_.Clinic.Management.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    //Tim kiem benh nhan theo nhieu truong
    @PersistenceContext
    private EntityManager entityManager;
    public List<PatientResponseDTO> searchPatient(PatientSearchDTO dto) {
        String sql = "select p from Patient p where 1=1";
        if (dto.getName() != null && !dto.getName().isBlank()) {
            sql += " and lower(p.name) like lower(concat('%', :name, '%'))";
        }
        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            sql += " and lower(p.address) like lower(concat('%', :address, '%'))";
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            sql += " and p.phone like concat('%', :phone, '%')";
        }
        if (dto.getGender() != null) {
            sql += " and p.gender = :gender";
        }

        TypedQuery<Patient> query = entityManager.createQuery(sql, Patient.class);
        if (dto.getName() != null && !dto.getName().isBlank()) {
            query.setParameter("name",  dto.getName());
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            query.setParameter("phone", dto.getPhone());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            query.setParameter("address", dto.getAddress());
        }
        List<Patient> patients = query.getResultList();
        return patientMapper.toResponseDTOList(patients);
    }
}
