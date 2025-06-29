package com.example.lesson101.repository;

import com.example.lesson101.dto.StudentSearchDTO;
import com.example.lesson101.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

@Repository
public class StudentSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> search(StudentSearchDTO studentSearchDTO) {
        StringBuilder sql = new StringBuilder("select * from Student where 1=1 ");
        List<Object> params = new ArrayList<>();
        int index = 1;

        if (studentSearchDTO.getName() != null && !studentSearchDTO.getName().isEmpty()) {
            sql.append(" and name = ?").append(index++).append(" ");
            params.add(studentSearchDTO.getName());
        }

        if (studentSearchDTO.getGender() != null && !studentSearchDTO.getGender().isEmpty()) {
            sql.append(" and gender = ?").append(index++).append(" ");
            params.add(studentSearchDTO.getGender());
        }

        if (studentSearchDTO.getClassId() != null) {
            sql.append(" and class_id = ?").append(index++).append(" ");
            params.add(studentSearchDTO.getClassId());
        }

        if (studentSearchDTO.getMinAge() != null) {
            sql.append(" and age >= ?").append(index++).append(" ");
            params.add(studentSearchDTO.getMinAge());
        }

        if (studentSearchDTO.getMaxAge() != null) {
            sql.append(" and age <= ?").append(index++).append(" ");
            params.add(studentSearchDTO.getMaxAge());
        }

        if (studentSearchDTO.getFromDate() != null && studentSearchDTO.getToDate() != null) {
            sql.append(" and enrollment_date between ?").append(index++).append(" and ?").append(index++).append(" ");
            params.add(studentSearchDTO.getFromDate());
            params.add(studentSearchDTO.getToDate());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Student.class);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }

        return (List<Student>) query.getResultList();


    }
}
