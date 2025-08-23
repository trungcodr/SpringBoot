package mini.project.ecomerce_backend.service.impl;

import mini.project.ecomerce_backend.config.FileStorageConfig;
import mini.project.ecomerce_backend.service.ProductImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private final FileStorageConfig fileStorageConfig;

    public ProductImageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();

        Path uploadPath = Paths.get(fileStorageConfig.getUploadDir());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        for (MultipartFile file : files) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // trả về full URL để FE gọi được
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(filename)
                    .toUriString();

            urls.add(fileUrl);
        }
        return urls;
    }

}
