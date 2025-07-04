package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import com.example.Lesson13_.Clinic.Management.mapper.AppointmentMapper;
import com.example.Lesson13_.Clinic.Management.repository.AppointmentRepository;
import com.example.Lesson13_.Clinic.Management.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.patientRepository = patientRepository;
    }

    public Appointment createAppointment(AppointmentRequestDTO requestDTO) {
        if (requestDTO.getPatientId() == null || requestDTO.getTime() == null) {
            throw new IllegalArgumentException("Thieu thong tin Id benh nhan hoac Date");
        }
        if(!patientRepository.existsById(requestDTO.getPatientId())) {
            throw new IllegalArgumentException("Benh nhan khong ton tai!");
        }
        Appointment appointment = appointmentMapper.toEntity(requestDTO);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Khong tim thay lich hen voi Id :" + id);
        }
        return appointment;
    }

    public Appointment updateAppointment(Long id, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Khong tim thay lich hen voi Id :" + id);
        }
        if (requestDTO.getPatientId() == null || requestDTO.getTime() == null) {
            throw new IllegalArgumentException("Thieu thong tin benh nhan hoac thoi gian hen!");
        }
        appointmentMapper.update(appointment, requestDTO);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Khong tim thay lich hen voi Id :" + id);
        }
        appointmentRepository.deleteById(id);
    }
}
