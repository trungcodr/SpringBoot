package com.example.lesson101.service;

import com.example.lesson101.dto.StudentSearchDTO;
import com.example.lesson101.dto.StudentWithClassDTO;
import com.example.lesson101.entity.Classes;
import com.example.lesson101.entity.Student;
import com.example.lesson101.mapper.StudentMapper;
import com.example.lesson101.repository.ClassRepository;
import com.example.lesson101.repository.StudentRepository;
import com.example.lesson101.repository.StudentSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final StudentSearchRepository studentSearchRepository;
    public StudentService(StudentRepository studentRepository, ClassRepository classRepository, StudentSearchRepository studentSearchRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.studentSearchRepository = studentSearchRepository;
    }

    //  Them 1 hoc sinh vao lop hoc
    public Student createStudent(Student student, Long classId) {
        // Kiem tra lop co ton tai hay khong
        boolean classExists = classRepository.existsById(classId);
        if (!classExists) {
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
        // Kiem tra ten khong duoc de trong
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new RuntimeException("Ten hoc sinh khong duoc de trong!");
        }

        student.setClassId(classId);
        return studentRepository.save(student);
    }

    // Them nhieu hoc sinh vao lop hoc
    public List<Student> createMultiStudent(List<Student> students, Long classId) {
        // Kiem tra lop co ton tai hay khong
        if (!classRepository.existsById(classId)) {
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
        List<Student> newStudents = new ArrayList<>();
        // Kiem tra ten khong duoc de trong
        for (Student student : students) {
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                throw new RuntimeException("Ten hoc sinh khong duoc de trong!");
            }
            // Gan classId cho tung hoc sinh
            student.setClassId(classId);
            newStudents.add(student);
        }
        // Luu tat ca hoc sinh vao db
        return studentRepository.saveAll(newStudents);
    }

    // Lay ra hoc sinh thong qua id(bao gom thong tin lop hoc)
    public StudentWithClassDTO getStudent(Long studentId) {
        // Tim hoc sinh
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new RuntimeException("Khong tim thay hoc sinh!");
        }

        // goi mapper
        Classes classes = null;
        if (student.getClassId() != null) {
            classes = classRepository.findById(student.getClassId()).orElse(null);
        }
        return StudentMapper.toStudentWithClassDTO(student, classes);
    }

    // Lay ra danh sach hoc sinh bao gom thong tin lop hoc
    public List<StudentWithClassDTO> getAllStudentsWithClass() {
        List<Student> students = studentRepository.findAll();
        List<StudentWithClassDTO> result = new ArrayList<>();

        for (Student student : students) {
            Classes classes = null;
            if (student.getClassId() != null) {
                classes = classRepository.findById(student.getClassId()).orElse(null);
            }
            result.add(StudentMapper.toStudentWithClassDTO(student, classes));
        }
        return result;
    }

    // Lay ra danh sach hoc sinh cua 1 lop hoc
    public List<Student> getStudentsByClassId(Long classId) {
        //Kiem tra lop co ton tai hay khong
        if (!classRepository.existsById(classId)) {
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
        return studentRepository.findByClassId(classId);
    }

    // Chuyen hoc sinh sang lop moi
    public Student transferStudentToNewClass(Long studentId, Long newClassId) {
        // Tim hoc sinh
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new RuntimeException("Khong tim thay hoc sinh!");
        }
        // Kiem tra lop moi co ton tai
        boolean classExists = classRepository.existsById(newClassId);
        if (!classExists) {
            throw new RuntimeException("Lop moi khong ton tai!");
        }

        //Gan hoc sinh cho lop moi
        student.setClassId(newClassId);
        // luu vao db
        return studentRepository.save(student);
    }


    // Tim kiem hoc sinh theo ten lop
    public List<Student> findStudentByClassName(String className) {
        List<Classes> classesList = classRepository.findClassesByName(className);
        if (classesList == null || classesList.isEmpty()) {
            throw new RuntimeException("Khong tim thay lop nao ten:" + className);
        }
        return studentRepository.findByClassId(classesList.get(0).getId());
    }

    //Tim kiem hoc sinh theo ten hoc sinh
    public List<Student> findStudentByName(String studentName) {
        List<Student> result = studentRepository.findByNameIgnoreCase(studentName);
        if (result == null || result.isEmpty()) {
            throw new RuntimeException("Khong tim thay  hoc sinh nao ten la: " + studentName);
        }
        return result;
    }

    // Su dung Pagination - Paging
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // Search nhieu truong
    public List<Student> searchStudent(StudentSearchDTO studentSearchDTO){
        List<Student> result = studentSearchRepository.search(studentSearchDTO);
        if (result == null || result.isEmpty()) {
            throw new RuntimeException("Khong tim thay hoc sinh nao!");
        }
        return result;
    }
}
