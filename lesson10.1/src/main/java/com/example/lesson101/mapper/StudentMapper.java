package com.example.lesson101.mapper;

import com.example.lesson101.dto.StudentWithClassDTO;
import com.example.lesson101.entity.Classes;
import com.example.lesson101.entity.Student;

public class StudentMapper {
    public static StudentWithClassDTO toStudentWithClassDTO(Student student, Classes classes) {
        StudentWithClassDTO dto = new StudentWithClassDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setGender(student.getGender());
        if (classes != null) {
            StudentWithClassDTO.ClassDTO classDTO = new StudentWithClassDTO.ClassDTO();
            classDTO.setId(classes.getId());
            classDTO.setName(classes.getName());
            classDTO.setTeacherName(classes.getTeacherName());
            classDTO.setAddress(classes.getAddress());

            dto.setClassInfo(classDTO);
        }
        return dto;
    }
}
