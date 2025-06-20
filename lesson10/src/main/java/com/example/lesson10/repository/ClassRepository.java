package com.example.lesson10.repository;

import com.example.lesson10.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classes,Long> {

}
