package com.example.lesson10.service;

import com.example.lesson10.entity.SchoolClass;
import com.example.lesson10.entity.Student;
import com.example.lesson10.repository.SchoolClassRepository;
import com.example.lesson10.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public Student addStudentToClass(Long classId, Student student) throws Exception {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new Exception("Class not found"));
        student.setClassObj(schoolClass);
        return studentRepository.save(student);
    }

    //Ham them nhieu hoc sinh moi
    public List<Student> addStudentsToClass(Long classId, List<Student> students) throws Exception {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new Exception("Class not found"));
        for (Student student : students) {
            student.setClassObj(schoolClass);
        }
        return studentRepository.saveAll(students);
    }

    //    Lay 1 hoc sinh thong qua id
    public Student getStudentById(Long studentId) throws Exception {
        return studentRepository.findById(studentId).
                orElseThrow(() -> new Exception("Student not found"));
    }

    //    Lay danh sach hoc sinh
    public List<Student> findAllStudents() throws Exception {
        return studentRepository.findAll();
    }

    //    Lay danh sach hoc sinh cua 1 lop hoc
    public List<Student> getStudentsByClassId(Long classId) {
        return studentRepository.findByClassObjId(classId);
    }

    //    Chuyen hoc sinh sang lop khac
    public Student transferStudentToClass(Long studentId, Long newClassId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("Student not found"));

        SchoolClass newClass = schoolClassRepository.findById(newClassId)
                .orElseThrow(() -> new Exception("Class not found"));

        student.setClassObj(newClass);
        return studentRepository.save(student);
    }


}
