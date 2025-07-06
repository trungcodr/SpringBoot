package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
    Appointment findAppointmentById(Long id);

    List<Appointment> id(Long id);

    @Query(value = """
            select count(*) from appointment
            where status = 'DONE'
            and month(time) = month(current_date())
            and year(time) = year(current_date())
            and time <= current_timestamp 
            """, nativeQuery = true
    )
    Long countAppointmentsInCurrentMonth();

    @Query(value = """
            select count(*) * 1.0 / count(distinct date(time))
            from appointment
            where status = 'PENDING'
            
        """, nativeQuery = true)
    Double getAverageAppointmentPerDay();
}
