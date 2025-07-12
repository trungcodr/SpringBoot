package com.example.Lesson13_.Clinic.Management.mapper;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.AppointmentResponseDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import com.example.Lesson13_.Clinic.Management.entity.Doctor;
import com.example.Lesson13_.Clinic.Management.enums.AppointmentStatus;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment toEntity(AppointmentRequestDTO dto) {
        if (dto == null) return null;

        Appointment appointment = new Appointment();
        appointment.setPatientId(dto.getPatientId());
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setTime(dto.getTime());
        appointment.setReason(dto.getReason());
        appointment.setNote(dto.getNote());
        if (dto.getStatus() != null) {
            appointment.setStatus(dto.getStatus());
        } else {
            appointment.setStatus(AppointmentStatus.PENDING);
        }
        return appointment;
    }

    public AppointmentResponseDTO toDTO(Appointment appointment, Doctor doctor) {
        if (appointment == null) return null;
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatientId());
        dto.setDoctorId(doctor.getId());
        dto.setDoctorName(doctor.getName());
        dto.setTime(appointment.getTime());
        dto.setReason(appointment.getReason());
        dto.setNote(appointment.getNote());
        dto.setStatus(appointment.getStatus());

        return dto;
    }

    public void update(Appointment appointment, AppointmentRequestDTO dto) {
        if (appointment == null || dto == null) return;
        appointment.setTime(dto.getTime());
        appointment.setReason(dto.getReason());
        appointment.setNote(dto.getNote());
        if (dto.getStatus() != null) {
            appointment.setStatus(dto.getStatus());
        }
    }
}
