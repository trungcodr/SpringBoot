package com.example.Lesson13_.Clinic.Management.mapper;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import com.example.Lesson13_.Clinic.Management.enums.AppointmentStatus;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment toEntity(AppointmentRequestDTO dto) {
        if (dto == null) return null;

        Appointment appointment = new Appointment();
        appointment.setPatientId(dto.getPatientId());
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
