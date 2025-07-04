package com.example.lesson10.service;

import com.example.lesson10.dto.StudentWithClassDTO;
import com.example.lesson10.entity.Classes;
import com.example.lesson10.entity.Student;
import com.example.lesson10.repository.ClassRepository;
import com.example.lesson10.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

//  Them 1 hoc sinh vao lop hoc
    public Student createStudent(Student student, long classId) {
//        Kiem tra lop co ton tai hay khong
        boolean classExists = classRepository.existsById(classId);
        if(!classExists){
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
//        Kiem tra ten khong duoc de trong
        if(student.getName() == null || student.getName().trim().isEmpty()){
            throw new RuntimeException("Ten hoc sinh khong duoc de trong!");
        }

        student.setClassId(classId);
        return studentRepository.save(student);
    }

//    Them nhieu hoc sinh vao lop hoc
    public List<Student> createMultiStudent(List<Student> students, long classId) {
//        Kiem tra lop co ton tai hay khong
        if(!classRepository.existsById(classId)){
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
        List<Student> newStudents = new ArrayList<>();
//        Kiem tra ten khong duoc de trong
        for(Student student : students){
            if (student.getName() == null || student.getName().trim().isEmpty()){
                throw new RuntimeException("Ten hoc sinh khong duoc de trong!");
            }
           // Gan classId cho tung hoc sinh
            student.setClassId(classId);
            newStudents.add(student);
        }
        // Luu tat ca hoc sinh vao db
        return studentRepository.saveAll(newStudents);
    }

//    Lay ra hoc sinh thong qua id(bao gom thong tin lop hoc)
    public StudentWithClassDTO getStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null){
            throw new RuntimeException("Khong tim thay hoc sinh!");
        }

        Classes classes = classRepository.findById(student.getClassId()).orElse(null);
        if(classes == null){
            throw new RuntimeException("Lop hoc khong ton tai!");
        }

        // Mapping
        StudentWithClassDTO studentWithClassDTO = new StudentWithClassDTO();
        studentWithClassDTO.setId(student.getId());
        studentWithClassDTO.setName(student.getName());
        studentWithClassDTO.setAge(student.getAge());
        studentWithClassDTO.setGender(student.getGender());

        StudentWithClassDTO.ClassDTO classDTO = new StudentWithClassDTO.ClassDTO();
        classDTO.setId(classes.getId());
        classDTO.setName(classes.getName());
        classDTO.setTeacherName(classes.getTeacherName());
        classDTO.setAddress(classes.getAddress());

        studentWithClassDTO.setClassInfo(classDTO);

        return studentWithClassDTO;
    }

    // Lay ra danh sach hoc sinh bao gom thong tin lop hoc
    public List<StudentWithClassDTO> getAllStudentsWithClass() {
        List<Student> students = studentRepository.findAll();
        List<StudentWithClassDTO> result = new ArrayList<>();
        for(Student student : students){
            StudentWithClassDTO studentWithClassDTO = new StudentWithClassDTO();
            studentWithClassDTO.setId(student.getId());
            studentWithClassDTO.setName(student.getName());
            studentWithClassDTO.setAge(student.getAge());
            studentWithClassDTO.setGender(student.getGender());

            // tim lop tuong ung voi classId
            Classes classes = classRepository.findById(student.getClassId()).orElse(null);
            if(classes != null){
                StudentWithClassDTO.ClassDTO classDTO = new StudentWithClassDTO.ClassDTO();
                classDTO.setId(classes.getId());
                classDTO.setName(classes.getName());
                classDTO.setTeacherName(classes.getTeacherName());
                classDTO.setAddress(classes.getAddress());

                studentWithClassDTO.setClassInfo(classDTO);
            }
            result.add(studentWithClassDTO);
        }
        return result;
    }

    // Lay ra danh sach hoc sinh cau 1 lop hoc
    public List<Student> getStudentsByClassId(long classId) {
        //Kiem tra lop co ton tai hay khong
        if(!classRepository.existsById(classId)){
            throw new RuntimeException("Lop hoc khong ton tai!");
        }
        return studentRepository.findByClassId(classId);
    }

    // Chuyen hoc sinh sang lop moi
    public Student transferStudentToNewClass(long studentId, long newClassId) {
        // Tim hocsinh
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null){
            throw new RuntimeException("Khong tim thay hoc sinh!");
        }
        // Kiem tra lop moi co ton tai
        boolean classExists = classRepository.existsById(newClassId);
        if(!classExists){
            throw new RuntimeException("Lop moi khong ton tai!");
        }

        //Gan hoc sinh cho lop moi
        student.setClassId(newClassId);
        // luu vao db
        return studentRepository.save(student);
    }


}
