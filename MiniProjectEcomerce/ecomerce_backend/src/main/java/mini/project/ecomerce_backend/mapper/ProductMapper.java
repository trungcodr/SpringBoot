package mini.project.ecomerce_backend.mapper;

import mini.project.ecomerce_backend.dto.ProductRequestDTO;
import mini.project.ecomerce_backend.dto.ProductResponseDTO;
import mini.project.ecomerce_backend.entity.Product;
import mini.project.ecomerce_backend.entity.ProductImage;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setId(productRequestDTO.getId());
        product.setCategoryId(productRequestDTO.getCategoryId());
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setThumbnail(productRequestDTO.getThumbnail());
        product.setShortDesc(productRequestDTO.getShortDesc());
        product.setDescription(productRequestDTO.getDescription());
        return product;
    }

    public static List<ProductImage> imagesToEntity(Product product, List<String> imageUrls) {
        return imageUrls.stream().map(url -> {
            ProductImage img = new ProductImage();
            img.setProductId(product.getId());
            img.setImageUrl(url);
            return img;
        }).collect(Collectors.toList());
    }

    public static ProductResponseDTO toResponseDTO(Product product, List<ProductImage> images) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setCategoryId(product.getCategoryId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setThumbnail(product.getThumbnail());
        dto.setShortDesc(product.getShortDesc());
        dto.setDescription(product.getDescription());
        List<String> imageUrls = images.stream().map(ProductImage::getImageUrl).toList();
        dto.setImages(imageUrls);
        return dto;
    }
}
