package com.example.lesson10.service;

import com.example.lesson10.entity.Classes;
import com.example.lesson10.entity.Student;
import com.example.lesson10.repository.ClassRepository;
import com.example.lesson10.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    //    Tao moi lop hoc
    public Classes createClass(Classes classes) {
        return classRepository.save(classes);
    }

    //    Kiem tra xem ten lop co bi trung khong
    public boolean isClassNameExist(String className) {
        List<Classes> classes = classRepository.findAll();
        for (Classes c : classes) {
            if (c.getName().equalsIgnoreCase(className)) {
                return true;
            }
        }
        return false;
    }

    //xoa lop khong cascade
//    public void deleteClassById(long classId) {
//        //kiem tra lop co ton tai khong
//        boolean classExists = classRepository.existsById(classId);
//        if (!classExists) {
//            throw new RuntimeException("Khong tim thay lop hoc!");
//        }
//        // Kiem tra lop co hoc sinh khong
//        int studentCount = studentRepository.countByClassId(classId);
//        if (studentCount > 0) {
//            throw new RuntimeException("Khong the xao lop vi co hoc sinh trong lop!");
//        }
//        //Xoa lop
//        classRepository.deleteById(classId);
//    }

    // xoa lop cascade
    public void deleteClassCascade(long clasId) {
        //Kiem tra lop co ton tai khong
        boolean exists = classRepository.existsById(clasId);
        if (!exists) {
            throw new RuntimeException("Khong tim thay lop hoc");
        }
        // lay danh sach hoc sinh trong lop
        List<Student> students = studentRepository.findByClassId(clasId);
        // neu co hoc sinh thi xoa het hoc sinh
        if (students.isEmpty()) {
            studentRepository.deleteAll(students);
        }
        //xoa lop
        classRepository.deleteById(clasId);
    }
}
