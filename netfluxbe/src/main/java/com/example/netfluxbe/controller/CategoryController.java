package com.example.netfluxbe.controller;

import com.example.netfluxbe.dto.CategoryResponseDTO;
import com.example.netfluxbe.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Map<String,Object> getCategories() {
        List<CategoryResponseDTO> items = categoryService.getAllCategories();
        Map<String,Object> response = new HashMap<>();
        response.put("items", items);
        return response;
    }
}
