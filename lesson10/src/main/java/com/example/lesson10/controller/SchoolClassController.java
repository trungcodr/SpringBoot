package com.example.lesson10.controller;


import com.example.lesson10.entity.SchoolClass;
import com.example.lesson10.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {
    @Autowired
    private SchoolClassService schoolClassService;
    @PostMapping
    public SchoolClass addSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.addSchoolClass(schoolClass);
    }
}
