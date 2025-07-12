package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.AppointmentResponseDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import com.example.Lesson13_.Clinic.Management.entity.Doctor;
import com.example.Lesson13_.Clinic.Management.mapper.AppointmentMapper;
import com.example.Lesson13_.Clinic.Management.repository.AppointmentRepository;
import com.example.Lesson13_.Clinic.Management.repository.DoctorRepository;
import com.example.Lesson13_.Clinic.Management.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // Tao lich hen moi
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        // Kiem tra du lieu
        if (requestDTO.getPatientId() == null || requestDTO.getTime() == null) {
            throw new IllegalArgumentException("Thieu thong tin Id benh nhan, DoctorId hoac Date");
        }
        // Kiem tra su ton tai cua benh nhan
        if(!patientRepository.existsById(requestDTO.getPatientId())) {
            throw new IllegalArgumentException("Benh nhan khong ton tai!");
        }
        //Kiem tra ton tai bac si
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId()).get();
        if (doctor == null) {
            throw new IllegalArgumentException("Bac si khong ton tai!");
        }
        //Chuyen tu DTO sang Entity
        Appointment appointment = appointmentMapper.toEntity(requestDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment,doctor);
    }

    // Lay ra tat ca lich hen
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Lay ra lich hen theo ID
    public Appointment getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Khong tim thay lich hen voi Id :" + id);
        }
        return appointment;
    }

    // Cap nhat lich hen
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

    // Xoa lich hen
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Khong tim thay lich hen voi Id :" + id);
        }
        appointmentRepository.deleteById(id);
    }

    // Toi can biet duoc trong thang nay co bao nhieu luot den kham
    public Long countAppointmentsThisMonth() {
        return appointmentRepository.countAppointmentsInCurrentMonth();
    }

    // Toi can biet duoc trung binh moi ngay co bao nhieui lich kham duoc dat
    public Double getAverageAppointmentPerDay(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.getAverageAppointmentPerDay(start, end);
    }

    // Toi can biet duoc trung binh moi ngay co bao nhieu luot den kham thuc te
    public Double getAverageDoneAppointment(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.getAverageDoneAppointment(start, end);
    }

    //Toi can biet ti le khach den thuc te so voi lich hen
    public Double getArrivalRateBetween(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.getArrivalRateBetween(start, end);
    }
}
