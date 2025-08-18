package com.example.netfluxbe.service.impl;

import com.example.netfluxbe.service.PosterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PosterServiceImpl implements PosterService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String uploadPoster(MultipartFile file) throws IOException {
        File folder = new File(uploadPath + "/posters");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destination = new File(folder, fileName);
        file.transferTo(destination);

        return "http://localhost:8080/uploads/posters/" + fileName;
    }
}
