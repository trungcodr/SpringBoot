package com.example.lesson10.repository;

import com.example.lesson10.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassId(Long classId);
    //Dem so hoc sinh trong 1 lop hjoc
    int countByClassId(Long classId);

}
