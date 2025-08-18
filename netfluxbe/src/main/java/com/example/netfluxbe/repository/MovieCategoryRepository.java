package com.example.netfluxbe.repository;

import com.example.netfluxbe.entity.MovieCategory;
import com.example.netfluxbe.entity.MovieCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, MovieCategoryId> {
    List<MovieCategory> findByMovieId(String movieId);
    Long countByCategoryId(String categoryId);

}
