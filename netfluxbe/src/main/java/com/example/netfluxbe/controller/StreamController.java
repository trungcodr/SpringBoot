package com.example.netfluxbe.controller;

import com.example.netfluxbe.service.SourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/stream")
public class StreamController {
    private final SourceService sourceService;
    public StreamController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/mp4/{sourceId}")
    public void streamVideo(@PathVariable Long sourceId,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        sourceService.streamVideo(sourceId, request, response);
    }


}
