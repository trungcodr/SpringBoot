package mini.project.ecomerce_backend.service.impl;

import mini.project.ecomerce_backend.dto.PaginationResponseDTO;
import mini.project.ecomerce_backend.dto.ProductRequestDTO;
import mini.project.ecomerce_backend.dto.ProductResponseDTO;
import mini.project.ecomerce_backend.entity.Product;
import mini.project.ecomerce_backend.entity.ProductImage;
import mini.project.ecomerce_backend.mapper.ProductMapper;
import mini.project.ecomerce_backend.repository.ProductImageRepository;
import mini.project.ecomerce_backend.repository.ProductRepository;
import mini.project.ecomerce_backend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.toEntity(productRequestDTO);
        product = productRepository.save(product);
        List<ProductImage> images = ProductMapper.imagesToEntity(product, productRequestDTO.getImages());
        productImageRepository.saveAll(images);
        return ProductMapper.toResponseDTO(product, images);
    }

    @Override
    public PaginationResponseDTO<ProductResponseDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page bắt đầu từ 0
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponseDTO> items = productPage.getContent().stream()
                .map(product -> {
                    List<ProductImage> images = productImageRepository.findByProductId(product.getId());
                    return ProductMapper.toResponseDTO(product, images);
                })
                .toList();

        return new PaginationResponseDTO<>(
                page,
                size,
                productPage.getTotalElements(),
                items
        );
    }
    
    @Override
    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        List<ProductImage> images = productImageRepository.findByProductId(id);
        return ProductMapper.toResponseDTO(product, images);
    }
}
