package com.example.lesson101.controller;


import com.example.lesson101.dto.StudentSearchDTO;
import com.example.lesson101.dto.StudentWithClassDTO;
import com.example.lesson101.entity.Student;
import com.example.lesson101.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // them 1 hoc sinh moi vao lop
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student, @RequestParam Long classId ) {
        try {
            Student save = studentService.createStudent(student, classId);
            return ResponseEntity.ok(save);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // them nhieu hoc sinh moi vao 1 lop
    @PostMapping("/batch")
    public ResponseEntity<?> createMultipleStudents(@RequestBody List<Student> students, @RequestParam Long classId) {
        try {
            List<Student> saveStudents = studentService.createMultiStudent(students, classId);
            return ResponseEntity.ok(saveStudents);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // lay hoc sinh thong qua id (bao gom thong tin lop)
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentWithClass(@PathVariable Long id) {
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
    public ResponseEntity<?> getAllStudentsByClass(@RequestParam Long classId) {
        try {
            List<Student> students = studentService.getStudentsByClassId(classId);
            return ResponseEntity.ok(students);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Chuyen hoc sinh sang lop moi
    @PutMapping("/transfer")
    public ResponseEntity<?> transferStudent(@RequestParam Long studentId, @RequestParam Long classId) {
        try {
            Student transfer = studentService.transferStudentToNewClass(studentId, classId);
            return ResponseEntity.ok(transfer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Tim kiem hoc sinh theo ten lop
    @GetMapping("/search-student")
    public ResponseEntity<?> searchStudent(@RequestParam String className) {
        try {
            List<Student> students = studentService.findStudentByClassName(className);
            return ResponseEntity.ok(students);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // tim kiem hoc sinh theo ten hoc sinh
    @GetMapping("/search")
    public ResponseEntity<?> searchStudentByName(@RequestParam String studentName) {
        try {
            List<Student> students = studentService.findStudentByName(studentName);
            return ResponseEntity.ok(students);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // phan trang pagination - paging
    @GetMapping("/paging")
    public ResponseEntity<?> getStudentWithPaging(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getAllStudents(pageRequest);
        return ResponseEntity.ok(studentPage);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody StudentSearchDTO studentSearchDTO) {
       try {
           List<Student> result  = studentService.searchStudent(studentSearchDTO);
           return ResponseEntity.ok(result);
       } catch (RuntimeException e) {
           return ResponseEntity.status(500).body(e.getMessage());
       }
    }
}
