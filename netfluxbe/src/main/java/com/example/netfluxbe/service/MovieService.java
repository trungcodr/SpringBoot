package com.example.netfluxbe.service;

import com.example.netfluxbe.dto.MovieDTO;
import com.example.netfluxbe.dto.MovieResponseDTO;
import com.example.netfluxbe.dto.PageResponseDTO;

import java.util.List;

public interface MovieService {
    PageResponseDTO<MovieResponseDTO> getAllMovies(int page, int size);

    MovieDTO getMovieById(String id);
    MovieDTO saveMovieMetadata(MovieDTO movieDTO);
}
