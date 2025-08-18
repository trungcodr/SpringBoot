package com.example.netfluxbe.controller;

import com.example.netfluxbe.entity.Source;
import com.example.netfluxbe.service.SourceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private final SourceService sourceService;
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE  )
    public ResponseEntity<Source> uploadSource(
            @RequestParam("file") MultipartFile file,
            @RequestParam("movieId") String movieId,
            @RequestParam(value = "episodeId", required = false) Long episodeId,
            @RequestParam("type") String type,
            @RequestParam("quality") String quality) {
        try {
            Source source = sourceService.uploadSource(file, movieId, episodeId, type, quality);
            return ResponseEntity.ok(source);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
