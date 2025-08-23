package mini.project.ecomerce_backend.mapper;

import mini.project.ecomerce_backend.dto.CategoryRequestDTO;
import mini.project.ecomerce_backend.dto.CategoryResponseDTO;
import mini.project.ecomerce_backend.entity.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        return new Category(categoryRequestDTO.getId(),categoryRequestDTO.getName(), false);
    }

    public static CategoryResponseDTO toDTO(Category  category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }

}
