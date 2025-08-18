package com.example.netfluxbe.mapper;

import com.example.netfluxbe.dto.EpisodeDTO;
import com.example.netfluxbe.dto.MovieDTO;
import com.example.netfluxbe.dto.MovieResponseDTO;
import com.example.netfluxbe.entity.Episode;
import com.example.netfluxbe.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {
    public MovieResponseDTO toMovieResponseDTO(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setSeries(movie.isSeries());
        dto.setDurationMinutes(movie.getDurationMinutes());
        dto.setPoster(movie.getPoster());
        dto.setRating(movie.getRating());
        dto.setDescription(movie.getDescription());
        dto.setYear(movie.getYear());

        return dto;
    }

    public static Movie toEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setYear(dto.getYear());
        movie.setSeries(dto.isSeries());
        movie.setDurationMinutes(dto.getDurationMinutes());
        movie.setPoster(dto.getPoster());
        movie.setRating(dto.getRating());
        movie.setDescription(dto.getDescription());
        return movie;
    }

    public static MovieDTO toDTO(Movie entity) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());
        dto.setSeries(entity.isSeries());
        dto.setDurationMinutes(entity.getDurationMinutes());
        dto.setPoster(entity.getPoster());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static EpisodeDTO toDTO(Episode episode) {
        EpisodeDTO dto = new EpisodeDTO();
        dto.setEpisodeId(episode.getId());
        dto.setName(episode.getName());
        return dto;
    }

}
