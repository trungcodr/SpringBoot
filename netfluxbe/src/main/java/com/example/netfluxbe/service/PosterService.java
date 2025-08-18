package com.example.netfluxbe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PosterService {
    String uploadPoster(MultipartFile file) throws IOException;


}
