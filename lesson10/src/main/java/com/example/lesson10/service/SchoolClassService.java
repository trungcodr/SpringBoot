package com.example.lesson10.service;

import com.example.lesson10.entity.SchoolClass;
import com.example.lesson10.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SchoolClassService {
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    public SchoolClass addSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }
    
}
