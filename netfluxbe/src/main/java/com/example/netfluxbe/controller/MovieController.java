package com.example.netfluxbe.controller;

import com.example.netfluxbe.dto.MovieDTO;
import com.example.netfluxbe.dto.MovieResponseDTO;
import com.example.netfluxbe.dto.PageResponseDTO;
import com.example.netfluxbe.service.MovieService;
import com.example.netfluxbe.service.PosterService;
import com.example.netfluxbe.service.SourceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final SourceService sourceService;
    private final PosterService posterService;
    public MovieController(MovieService movieService, SourceService sourceService, PosterService posterService ) {
        this.movieService = movieService;
        this.sourceService = sourceService;
        this.posterService = posterService;
    }


    @GetMapping()
    public PageResponseDTO<MovieResponseDTO> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return movieService.getAllMovies(page, size);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/{id}/sources")
    public ResponseEntity<?> getMovieSources(
            @PathVariable("id") String movieId,
            @RequestParam(defaultValue = "false") boolean series) {
        Object response = sourceService.getSourceByMovieId(movieId, series);
        return ResponseEntity.ok(response);
    }

    //upload poster
    @PostMapping(
            value = "/upload-poster",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPoster(@RequestParam("poster") MultipartFile file) {
        try {
            String posterUrl = posterService.uploadPoster(file);
            return ResponseEntity.ok(posterUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading poster: " + e.getMessage());
        }
    }

    //Luu metadata phim
    @PostMapping("/save-metadata")
    public ResponseEntity<MovieDTO> saveMovieMetadata(@RequestBody MovieDTO movieDTO) {
        MovieDTO responseDTO = movieService.saveMovieMetadata(movieDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
