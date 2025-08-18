package com.example.netfluxbe.service.impl;


import com.example.netfluxbe.dto.EpisodeSourceResponse;
import com.example.netfluxbe.dto.MovieSourceResponse;
import com.example.netfluxbe.dto.SeriesSourceResponse;
import com.example.netfluxbe.dto.SourceDTO;
import com.example.netfluxbe.entity.Episode;
import com.example.netfluxbe.entity.Source;
import com.example.netfluxbe.repository.EpisodeRepository;
import com.example.netfluxbe.repository.SourceRepository;
import com.example.netfluxbe.service.SourceService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;


@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;
    private final EpisodeRepository episodeRepository;
    public SourceServiceImpl(SourceRepository sourceRepository, EpisodeRepository episodeRepository) {
        this.sourceRepository = sourceRepository;
        this.episodeRepository = episodeRepository;
    }

    @Value("${upload.path}")
    private String uploadPath; // Đường dẫn lưu trữ file tải lên da cau hinh trong application.properties

    @Override
    public Object getSourceByMovieId(String movieId, boolean isSeries) {
        if (!isSeries) { // phim lẻ
            List<Source> sources = sourceRepository.findByMovieId(movieId);
            MovieSourceResponse response = new MovieSourceResponse();
            response.setItems(
                    sources.stream().map(s -> {
                        SourceDTO dto = new SourceDTO();
                        dto.setType(s.getType());
                        dto.setQuality(s.getQuality());
                        dto.setUrl(s.getUrl());
                        return dto;
                    }).toList()
            );
            return response;
        } else { // phim series
            List<Episode> episodes = episodeRepository.findByMovieId(movieId);

            List<EpisodeSourceResponse> episodeResponses = episodes.stream().map(e -> {
                List<Source> sources = sourceRepository.findByEpisodeId(e.getId());

                List<SourceDTO> items = sources.stream().map(s -> {
                    SourceDTO dto = new SourceDTO();
                    dto.setType(s.getType());
                    dto.setQuality(s.getQuality());
                    dto.setUrl(s.getUrl());
                    return dto;
                }).toList();

                EpisodeSourceResponse er = new EpisodeSourceResponse();
                er.setEpisode(e.getEpisodeNumber());
                er.setName(e.getName());
                er.setItems(items);
                return er;
            }).toList();

            SeriesSourceResponse response = new SeriesSourceResponse();
            response.setEpisodes(episodeResponses);
            return response;
        }
    }

    @Override
    public Source uploadSource(MultipartFile file, String movieId, Long episodeId, String type, String quality) throws IOException{
        File folder = new File(uploadPath + "/videos");
        if (!folder.exists()) {
            folder.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destinationFile = new File(folder, fileName);
        file.transferTo(destinationFile); // Lưu file vào thư mục đã tạo

        String fileUrl = "http://localhost:8080/uploads/videos/" + fileName; // Đường dẫn URL của file đã tải lên
        Source source = new Source();
        source.setMovieId(movieId);
        source.setEpisodeId(episodeId);
        source.setType(type);
        source.setQuality(quality);
        source.setUrl(fileUrl);
        return sourceRepository.save(source); // Lưu thông tin nguồn vào cơ sở dữ liệu
    }

    @Override
    public void streamVideo(Long sourceId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Source source = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Source not found"));

        String relativePath = source.getUrl().replace("/uploads", "");
        File videoFile = new File("D:/netflux/uploads/videos/1755521821905_T1 (1080p, h264).mp4" );

        if (!videoFile.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Sử dụng Range để stream partial content
        long fileLength = videoFile.length();
        String range = request.getHeader("Range");
        long start = 0, end = fileLength - 1;

        if (range != null) {
            String[] parts = range.replace("bytes=", "").split("-");
            start = Long.parseLong(parts[0]);
            if (parts.length > 1) {
                end = Long.parseLong(parts[1]);
            }
        }

        long contentLength = end - start + 1;
        response.setStatus(range != null ? 206 : 200);
        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Length", String.valueOf(contentLength));
        response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);

        try (RandomAccessFile raf = new RandomAccessFile(videoFile, "r");
             ServletOutputStream out = response.getOutputStream()) {
            raf.seek(start);
            byte[] buffer = new byte[1024 * 16];
            long remaining = contentLength;
            int read;
            while (remaining > 0 && (read = raf.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                out.write(buffer, 0, read);
                remaining -= read;
            }
        }
    }

}
