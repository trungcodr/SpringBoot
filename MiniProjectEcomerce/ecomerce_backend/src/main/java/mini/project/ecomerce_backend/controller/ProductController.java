package mini.project.ecomerce_backend.controller;

import mini.project.ecomerce_backend.dto.PaginationResponseDTO;
import mini.project.ecomerce_backend.dto.ProductRequestDTO;
import mini.project.ecomerce_backend.dto.ProductResponseDTO;
import mini.project.ecomerce_backend.service.ProductImageService;
import mini.project.ecomerce_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductImageService productImageService;
    private final ProductService productService;
    public ProductController(ProductImageService productImageService, ProductService productService) {
        this.productImageService = productImageService;
        this.productService = productService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        try {
            List<String> urls = productImageService.uploadImages(files);
            return ResponseEntity.ok(urls);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Upload failed: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO response = productService.createProduct(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size) {

        PaginationResponseDTO<ProductResponseDTO> response = productService.getAllProducts(page, size);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
        ProductResponseDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    
}
