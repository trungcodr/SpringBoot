package com.example.netfluxbe.service.impl;

import com.example.netfluxbe.dto.EpisodeDTO;
import com.example.netfluxbe.dto.MovieDTO;
import com.example.netfluxbe.dto.MovieResponseDTO;
import com.example.netfluxbe.dto.PageResponseDTO;
import com.example.netfluxbe.entity.Category;
import com.example.netfluxbe.entity.Episode;
import com.example.netfluxbe.entity.Movie;
import com.example.netfluxbe.entity.MovieCategory;
import com.example.netfluxbe.mapper.MovieMapper;
import com.example.netfluxbe.repository.CategoryRepository;
import com.example.netfluxbe.repository.EpisodeRepository;
import com.example.netfluxbe.repository.MovieCategoryRepository;
import com.example.netfluxbe.repository.MovieRepository;
import com.example.netfluxbe.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieCategoryRepository movieCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final EpisodeRepository episodeRepository;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, MovieCategoryRepository movieCategoryRepository, CategoryRepository categoryRepository, EpisodeRepository episodeRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieCategoryRepository = movieCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.episodeRepository = episodeRepository;
    }


    @Override
    public PageResponseDTO<MovieResponseDTO> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page trong request tính từ 1
        Page<Movie> moviePage = movieRepository.findAll(pageable);

        List<MovieResponseDTO> items = moviePage.getContent().stream().map(movie -> {
            List<MovieCategory> categories = movieCategoryRepository.findByMovieId(movie.getId());

            List<String> categoryNames = categories.stream()
                    .map(MovieCategory::getCategoryId)
                    .collect(Collectors.toList());

            MovieResponseDTO dto = movieMapper.toMovieResponseDTO(movie);
            dto.setCategories(categoryNames);

            return dto;
        }).collect(Collectors.toList());

        return new PageResponseDTO<>(
                page,
                size,
                moviePage.getTotalElements(),
                items
        );
    }

    @Override
    public MovieDTO getMovieById(String id) {
        Optional<Movie> movieOpt = movieRepository.findById(id);
        if (movieOpt.isEmpty()) {
            throw new RuntimeException("Movie not found with id: " + id);
        }

        Movie movie = movieOpt.get();

        // Lấy categories
        List<MovieCategory> movieCategories = movieCategoryRepository.findByMovieId(id);
        List<String> categories = movieCategories.stream()
                .map(mc -> {
                    Category c = categoryRepository.findById(mc.getCategoryId()).orElse(null);
                    return c != null ? c.getSlug() : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Lấy episodes nếu là series
        List<EpisodeDTO> episodes = null;
        Integer episodesCount = null;
        Integer durationMinutes = null;

        if (movie.isSeries()) {
            List<Episode> eps = episodeRepository.findByMovieId(movie.getId());
            episodes = eps.stream()
                    .map(e -> new EpisodeDTO((long) e.getEpisodeNumber(), e.getName()))
                    .collect(Collectors.toList());
            episodesCount = episodes.size();
        } else {
            durationMinutes = movie.getDurationMinutes();
        }

        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.isSeries(),
                episodesCount,
                durationMinutes,
                categories,
                movie.getPoster(),
                movie.getRating(),
                movie.getDescription(),
                episodes
        );
    }

    @Override
    public MovieDTO saveMovieMetadata(MovieDTO movieDTO) {
        // 1. Lưu movie
        Movie movie = MovieMapper.toEntity(movieDTO);
        Movie savedMovie = movieRepository.save(movie);


        // 3. Lưu category mới
        if (movieDTO.getCategories() != null) {
            for (String categoryId : movieDTO.getCategories()) {
                MovieCategory mc = new MovieCategory();
                mc.setMovieId(savedMovie.getId());
                mc.setCategoryId(categoryId);
                movieCategoryRepository.save(mc);
            }
        }


        // 5. Lưu episodes mới
        if (movieDTO.getEpisodes() != null) {
            for (EpisodeDTO epDTO : movieDTO.getEpisodes()) {
                Episode ep = new Episode();
                ep.setMovieId(savedMovie.getId());
                ep.setName(epDTO.getName());
                episodeRepository.save(ep);
            }
        }

        // 6. Query lại categories & episodes
        List<String> savedCategories = movieCategoryRepository.findByMovieId(savedMovie.getId())
                .stream().map(MovieCategory::getCategoryId).toList();

        List<EpisodeDTO> savedEpisodes = episodeRepository.findByMovieId(savedMovie.getId())
                .stream().map(MovieMapper::toDTO).toList();

        MovieDTO responseDTO = MovieMapper.toDTO(savedMovie);
        responseDTO.setCategories(savedCategories);
        responseDTO.setEpisodes(savedEpisodes);

        return responseDTO;
    }
}
