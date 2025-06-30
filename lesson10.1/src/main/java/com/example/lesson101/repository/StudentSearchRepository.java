package com.example.lesson101.repository;

import com.example.lesson101.dto.StudentSearchDTO;
import com.example.lesson101.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

@Repository
public class StudentSearchRepository {

    @PersistenceContext 
    private EntityManager entityManager;

    public List<Student> search(StudentSearchDTO studentSearchDTO) {

        // Khởi tạo câu SQL với một điều kiện TRUE mặc định để dễ dàng thêm AND
        StringBuilder sql = new StringBuilder("SELECT s.* FROM Student s WHERE 1=1");

        // --- Thêm các điều kiện tìm kiếm và tham số có tên ---

        if (StringUtils.hasText(studentSearchDTO.getName())) {
            sql.append(" AND s.name LIKE :studentName");
        }

        if (StringUtils.hasText(studentSearchDTO.getGender())) {
            sql.append(" AND s.gender = :gender");
        }

        if (studentSearchDTO.getClassId() != null) {
            sql.append(" AND s.class_id = :classId");
        }

        if (studentSearchDTO.getMinAge() != null) {
            sql.append(" AND s.age >= :minAge");
        }

        if (studentSearchDTO.getMaxAge() != null) {
            sql.append(" AND s.age <= :maxAge");
        }

        if (studentSearchDTO.getFromDate() != null && studentSearchDTO.getToDate() != null) {
            sql.append(" AND s.enrollment_date BETWEEN :fromDate AND :toDate");
        }

        // --- Tạo Query và thiết lập tham số ---
        Query query = entityManager.createNativeQuery(sql.toString(), Student.class);

        // Chỉ thiết lập tham số nếu điều kiện tương ứng có giá trị
        if (StringUtils.hasText(studentSearchDTO.getName())) {
            query.setParameter("studentName", "%" + studentSearchDTO.getName() + "%");
        }

        if (StringUtils.hasText(studentSearchDTO.getGender())) {
            query.setParameter("gender", studentSearchDTO.getGender());
        }

        if (studentSearchDTO.getClassId() != null) {
            query.setParameter("classId", studentSearchDTO.getClassId());
        }

        if (studentSearchDTO.getMinAge() != null) {
            query.setParameter("minAge", studentSearchDTO.getMinAge());
        }

        if (studentSearchDTO.getMaxAge() != null) {
            query.setParameter("maxAge", studentSearchDTO.getMaxAge());
        }

        if (studentSearchDTO.getFromDate() != null && studentSearchDTO.getToDate() != null) {
            query.setParameter("fromDate", studentSearchDTO.getFromDate());
            query.setParameter("toDate", studentSearchDTO.getToDate());
        }

        // --- Thực thi và trả về kết quả ---
        return (List<Student>) query.getResultList();


    }
}
