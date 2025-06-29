package com.example.lesson101.repository;

import com.example.lesson101.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassId(Long classId);
    //Dem so hoc sinh trong 1 lop hoc
    int countByClassId(Long classId);

    List<Student> findByNameIgnoreCase(String studentName);

    Page<Student> findAll(Pageable pageable);
}
