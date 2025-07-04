package com.example.Lesson13_.Clinic.Management.mapper;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
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
        return appointment;
    }

    public void update(Appointment appointment, AppointmentRequestDTO dto) {
        if (appointment == null || dto == null) return;
        appointment.setTime(dto.getTime());
        appointment.setReason(dto.getReason());
        appointment.setNote(dto.getNote());
    }
}
