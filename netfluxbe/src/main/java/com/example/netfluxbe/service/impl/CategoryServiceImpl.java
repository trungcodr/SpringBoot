package com.example.netfluxbe.service.impl;

import com.example.netfluxbe.dto.CategoryResponseDTO;
import com.example.netfluxbe.entity.Category;
import com.example.netfluxbe.repository.CategoryRepository;
import com.example.netfluxbe.repository.MovieCategoryRepository;
import com.example.netfluxbe.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MovieCategoryRepository movieCategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MovieCategoryRepository movieCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.movieCategoryRepository = movieCategoryRepository;
    }


    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> {
                    long count = movieCategoryRepository.countByCategoryId(category.getId());
                    return new CategoryResponseDTO(
                          category.getId(),
                          category.getSlug(),
                          category.getName(),
                            count
                    );
                })
                .collect(Collectors.toList());

    }
}
