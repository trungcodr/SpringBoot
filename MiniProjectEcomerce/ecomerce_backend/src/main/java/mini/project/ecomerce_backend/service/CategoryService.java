package mini.project.ecomerce_backend.service;

import mini.project.ecomerce_backend.dto.CategoryRequestDTO;
import mini.project.ecomerce_backend.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(String id, CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(String id);
    CategoryResponseDTO restoreCategory(String id); // Method to restore a deleted category
}
