package com.example.lesson101.repository;

import com.example.lesson101.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classes,Long> {
    List<Classes> findClassesByName(String name);
}
