package com.example.netfluxbe.repository;

import com.example.netfluxbe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
