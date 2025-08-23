package mini.project.ecomerce_backend.service.impl;

import mini.project.ecomerce_backend.dto.CategoryRequestDTO;
import mini.project.ecomerce_backend.dto.CategoryResponseDTO;
import mini.project.ecomerce_backend.entity.Category;
import mini.project.ecomerce_backend.mapper.CategoryMapper;
import mini.project.ecomerce_backend.repository.CategoryRepository;
import mini.project.ecomerce_backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = CategoryMapper.toEntity(categoryRequestDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findByDeletedFalse();
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(String id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setName(categoryRequestDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setDeleted(true); // Assuming you have a 'deleted' field in your Category entity
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDTO restoreCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        if (!category.isDeleted()) {
            throw new RuntimeException("Category is already active with id: " + id);
        }
        category.setDeleted(false); // Restore the category
        Category restoredCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(restoredCategory);
    }
}
