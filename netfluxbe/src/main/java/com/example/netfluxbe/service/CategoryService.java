package com.example.netfluxbe.service;

import com.example.netfluxbe.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
}
