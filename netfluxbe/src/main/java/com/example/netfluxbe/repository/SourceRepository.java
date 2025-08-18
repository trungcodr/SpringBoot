package com.example.netfluxbe.repository;

import com.example.netfluxbe.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findByMovieId(String movieId);
    List<Source> findByEpisodeId(Long episodeId);
}
