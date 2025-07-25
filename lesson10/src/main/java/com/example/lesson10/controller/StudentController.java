package com.example.lesson10.controller;

import com.example.lesson10.dto.StudentWithClassDTO;
import com.example.lesson10.entity.Student;
import com.example.lesson10.service.ClassService;
import com.example.lesson10.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // them 1 hoc sinh moi vao lop
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student, @RequestParam long classId ) {
        try {
            Student save = studentService.createStudent(student, classId);
            return ResponseEntity.ok(save);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // them nhieu hoc sinh moi vao 1 lop
    @PostMapping("/batch")
    public ResponseEntity<?> createMultipleStudents(@RequestBody List<Student> students, @RequestParam long classId) {
        try {
            List<Student> saveStudents = studentService.createMultiStudent(students, classId);
            return ResponseEntity.ok(saveStudents);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // lay hoc sinh thong qua id (bao gom thong tin lop)
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentWithClass(@PathVariable long id) {
        try {
            StudentWithClassDTO studentWithClassDTO = studentService.getStudent(id);
            return ResponseEntity.ok(studentWithClassDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lay ra danh sach hoc sinh bao gom thong tin lop hoc
    @GetMapping
    public ResponseEntity<?> getAllStudentsWithClass() {
        List<StudentWithClassDTO> result = studentService.getAllStudentsWithClass();
        return ResponseEntity.ok(result);
    }

    // Lay ra danh sach hoc sinh cua 1 lop khong bao gom thong tin lop
    @GetMapping("/by-class")
    public ResponseEntity<?> getAllStudentsByClass(@RequestParam long classId) {
        try {
            List<Student> students = studentService.getStudentsByClassId(classId);
            return ResponseEntity.ok(students);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Chuyen hoc sinh sang lop moi
    @PutMapping("/transfer")
    public ResponseEntity<?> transferStudent(@RequestParam long studentId, @RequestParam long classId) {
        try {
            Student transfer = studentService.transferStudentToNewClass(studentId, classId);
            return ResponseEntity.ok(transfer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
