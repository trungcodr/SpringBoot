package com.example.Lesson13_.Clinic.Management.mapper;

import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordResponseDTO;
import com.example.Lesson13_.Clinic.Management.dto.MedicalRecordWithDoctorDTO;
import com.example.Lesson13_.Clinic.Management.entity.MedicalRecord;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {
    public MedicalRecord toEntity (MedicalRecordRequestDTO dto) {
        if (dto == null) return null;
        MedicalRecord record = new MedicalRecord();
        record.setPatientId(dto.getPatientId());
        record.setDoctorId(dto.getDoctorId());
        record.setDate(dto.getDate());
        record.setDiagnosis(dto.getDiagnosis());
        record.setSymptoms(dto.getSymptoms());
        record.setPrescription(dto.getPrescription());
        record.setNote(dto.getNote());
        return record;
    }

    public MedicalRecordResponseDTO toDTO (MedicalRecord record) {
        if (record == null) return null;
        MedicalRecordResponseDTO dto = new MedicalRecordResponseDTO();
        dto.setId(record.getId());
        dto.setPatientId(record.getPatientId());
        dto.setDoctorId(record.getDoctorId());
        dto.setDate(record.getDate());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setSymptoms(record.getSymptoms());
        dto.setPrescription(record.getPrescription());
        dto.setNote(record.getNote());
        return dto;
    }

    public MedicalRecordWithDoctorDTO toWithDoctorDTO(MedicalRecord record, String doctorName) {
        MedicalRecordWithDoctorDTO dto = new MedicalRecordWithDoctorDTO();
        dto.setId(record.getId());
        dto.setDate(record.getDate());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setSymptoms(record.getSymptoms());
        dto.setPrescription(record.getPrescription());
        dto.setNote(record.getNote());
        dto.setDoctorId(record.getDoctorId());
        dto.setDoctorName(doctorName);
        return dto;
    }
    public void update(MedicalRecord record, MedicalRecordRequestDTO dto) {
        if (record == null || dto == null) return;
        record.setPatientId(dto.getPatientId());
        record.setDoctorId(dto.getDoctorId());
        record.setDate(dto.getDate());
        record.setDiagnosis(dto.getDiagnosis());
        record.setSymptoms(dto.getSymptoms());
        record.setPrescription(dto.getPrescription());
        record.setNote(dto.getNote());
    }
}
