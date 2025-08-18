package com.example.netfluxbe.service;


import com.example.netfluxbe.dto.SourceDTO;
import com.example.netfluxbe.entity.Source;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SourceService {
    Object getSourceByMovieId(String movieId, boolean isSeries);

    Source uploadSource(MultipartFile file,
                        String movieId,
                        Long episodeId,
                        String type,
                        String quality) throws IOException;

    void streamVideo(Long sourceId, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
