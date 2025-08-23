package mini.project.ecomerce_backend.service;

import mini.project.ecomerce_backend.dto.PaginationResponseDTO;
import mini.project.ecomerce_backend.dto.ProductRequestDTO;
import mini.project.ecomerce_backend.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(String id);

    PaginationResponseDTO<ProductResponseDTO> getAllProducts(int page, int size);
}
