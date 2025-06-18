package com.example.lesson10.repository;

import com.example.lesson10.entity.Student;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
    List<Student> findByClassObjId(Long classId);
}
