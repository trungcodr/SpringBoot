package mini.project.ecomerce_backend.service;

import mini.project.ecomerce_backend.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {
    List<String> uploadImages(List<MultipartFile> files) throws IOException;

}
