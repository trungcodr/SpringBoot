package mini.project.ecomerce_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mini.project.ecomerce_backend.dto.CategoryRequestDTO;
import mini.project.ecomerce_backend.dto.CategoryResponseDTO;
import mini.project.ecomerce_backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Quản lý danh mục", description = "Các API quản lý danh mục sản phẩm")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Tạo danh mục mới",
            description = "Tạo một danh mục sản phẩm mới trong hệ thống"
    )
    @PostMapping
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.createCategory(categoryRequestDTO);
    }

    @Operation(
            summary = "Lấy tất cả danh mục",
            description = "Lấy danh sách tất cả các danh mục sản phẩm trong hệ thống"
    )
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(
            summary = "Cập nhật danh mục",
            description = "Cập nhật thông tin của một danh mục sản phẩm theo ID"
    )
    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable String id,
                                              @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.updateCategory(id, categoryRequestDTO);
    }

    @Operation(
            summary = "Xóa danh mục",
            description = "Xóa một danh mục sản phẩm theo ID (xóa mềm)"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Khôi phục danh mục",
            description = "Khôi phục một danh mục sản phẩm đã bị xóa theo ID"
    )
    @PutMapping("/restore/{id}")
    public ResponseEntity<CategoryResponseDTO> restoreCategory(@PathVariable String id) {
        CategoryResponseDTO restoredCategory = categoryService.restoreCategory(id);
        return ResponseEntity.ok(restoredCategory);
    }
}
