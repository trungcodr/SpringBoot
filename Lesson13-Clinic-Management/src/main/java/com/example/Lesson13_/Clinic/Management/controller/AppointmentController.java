package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.AppointmentRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import com.example.Lesson13_.Clinic.Management.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentService.createAppointment(requestDTO);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,@RequestBody AppointmentRequestDTO requestDTO) {
        Appointment updated = appointmentService.updateAppointment(id,requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Xoa lich hen thanh cong");
    }

    @GetMapping("/count-this-month")
    public ResponseEntity<Long> countAppointmentsThisMonth() {
        return ResponseEntity.ok(appointmentService.countAppointmentsThisMonth());
    }

    @GetMapping("/average-per-day")
    public ResponseEntity<Double> averageAppointmentPerDay() {
        return ResponseEntity.ok(appointmentService.getAverageAppointmentPerDay());
    }
}
