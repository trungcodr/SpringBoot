package com.example.Lesson13_.Clinic.Management.repository;

import com.example.Lesson13_.Clinic.Management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
                and time between :start and :end
            """, nativeQuery = true)
    Double getAverageAppointmentPerDay(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
            );

    @Query(value = """
            select count(*) * 1.0 / count(distinct date (time))
            from appointment
            where status = 'DONE'
            and time between :start and :end
            and time <= current_timestamp
             """, nativeQuery = true)
    Double getAverageDoneAppointment(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = """
            select
                  (select count(*) from appointment
                   where status = 'DONE'
                   and time between :start and :end) * 100.0
            /
                   (select count(*) from appointment
                   where time between :start and :end)
                  """, nativeQuery = true)
    Double getArrivalRateBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );


}
