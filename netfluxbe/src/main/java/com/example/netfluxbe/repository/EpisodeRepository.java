package com.example.netfluxbe.repository;

import com.example.netfluxbe.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByMovieId(String movieId);
}
