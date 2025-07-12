package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordResponseDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordWithDoctorDTO;
import com.example.Lesson13_.Clinic.Management.entity.Doctor;
import com.example.Lesson13_.Clinic.Management.entity.MedicalRecord;
import com.example.Lesson13_.Clinic.Management.entity.Patient;
import com.example.Lesson13_.Clinic.Management.mapper.MedicalRecordMapper;
import com.example.Lesson13_.Clinic.Management.repository.DoctorRepository;
import com.example.Lesson13_.Clinic.Management.repository.MedicalRecordRepository;
import com.example.Lesson13_.Clinic.Management.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public MedicalRecord createMedicalRecord(MedicalRecordRequestDTO requestDTO) {
        if (requestDTO.getPatientId() == null || requestDTO.getDoctorId() == null) {
            throw new IllegalArgumentException("Thieu thong tin bat buoc");
        }
        Patient patient = patientRepository.findPatientById(requestDTO.getPatientId());
        if (patient == null) {
            throw new IllegalArgumentException("Khong tim thay benh nhan voi id la : " + requestDTO.getPatientId());
        }
        Doctor doctor = doctorRepository.findDoctorById(requestDTO.getDoctorId());
        if (doctor == null) {
            throw new IllegalArgumentException("Khong tim thay bac so voi id la : " + requestDTO.getDoctorId());
        }
        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(requestDTO);
        return medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecordResponseDTO> getAllMedicalRecordsById(Long patientId) {
        Patient patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Khong ton tai benh nhan voi id la : " + patientId);
        }
        List<MedicalRecord> records = medicalRecordRepository.findByPatientId(patientId);
        List<MedicalRecordResponseDTO> result = new ArrayList<>();
        for (MedicalRecord record : records) {
            result.add(medicalRecordMapper.toDTO(record));
        }
        return result;
    }

    public List<MedicalRecordWithDoctorDTO> getAllRecordsWithDoctorName() {
        List<MedicalRecord> records = medicalRecordRepository.findAll();
        List<MedicalRecordWithDoctorDTO> result = new ArrayList<>();

        for (MedicalRecord r : records) {
            Doctor doctor = doctorRepository.findDoctorById(r.getDoctorId());
            String doctorName = (doctor != null) ? doctor.getName() : "Khong ro";
            result.add(medicalRecordMapper.toWithDoctorDTO(r, doctorName));
        }

        return result;
    }

    public MedicalRecordResponseDTO update(Long id, MedicalRecordRequestDTO dto) {
        MedicalRecord record = medicalRecordRepository.findById(id).orElse(null);
        if (record == null) {
            throw new IllegalArgumentException("Khong tim thay benh an voi Id: " + id);
        }

        Patient patient = patientRepository.findPatientById(dto.getPatientId());
        if (patient == null) {
            throw new IllegalArgumentException("Khong tim thay benh nhan voi Id: " + dto.getPatientId());
        }

        Doctor doctor = doctorRepository.findDoctorById(dto.getDoctorId());
        if (doctor == null) {
            throw new IllegalArgumentException("Khong tim thay bac si voi Id: " + dto.getDoctorId());
        }

        medicalRecordMapper.update(record, dto);
        medicalRecordRepository.save(record);
        return medicalRecordMapper.toDTO(record);
    }

    public String getMostDiagnosis(LocalDateTime start, LocalDateTime end) {
        return medicalRecordRepository.findMostDiagnosis(start, end);
    }
}
