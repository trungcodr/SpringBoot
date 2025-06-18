package com.example.lesson10.controller;

import com.example.lesson10.entity.Student;
import com.example.lesson10.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/class/{classId}")
    public Student addStudentToClass(@PathVariable Long classId, @RequestBody Student student) throws Exception {
        return studentService.addStudentToClass(classId, student);
    }

    @PostMapping("/class/{classId}/bulk")
    public List<Student> addStudentsToClass(
            @PathVariable Long classId,
            @RequestBody List<Student> students) throws Exception {
        return studentService.addStudentsToClass(classId, students);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) throws Exception {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<Student> getAllStudents() throws Exception {
        return studentService.findAllStudents();
    }

    @GetMapping("/class/{classId}")
    public List<Student> getStudentsByClass(@PathVariable Long classId) {
        return studentService.getStudentsByClassId(classId);
    }

    @PutMapping("/{studentId}/transfer/{newClassId}")
    public Student transferStudent(@PathVariable Long studentId, @PathVariable Long newClassId) throws Exception {
        return studentService.transferStudentToClass(studentId, newClassId);
    }


}
