package com.example.lesson10.controller;

import com.example.lesson10.entity.Classes;
import com.example.lesson10.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody Classes newClass) {
        if (classService.isClassNameExist(newClass.getName())) {
            return ResponseEntity.badRequest().body("Ten lop da ton tai!");
        }
        Classes save = classService.createClass(newClass);
        return ResponseEntity.ok(save);
    }

    // Xoa lop khong cascade
//    @DeleteMapping("/{classId}")
//    public ResponseEntity<?> deleteClass(@PathVariable long classId) {
//        try {
//            classService.deleteClassById(classId);
//            return ResponseEntity.ok("Xoa lop thanh cong!");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    // xoa lop cascade
    @DeleteMapping("/cascade/{classId}")
    public ResponseEntity<?> deleteClassCascade(@PathVariable Long classId) {
        try {
            classService.deleteClassCascade(classId);
            return ResponseEntity.ok("Da xoa lop va hoc sinh trong lop!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
