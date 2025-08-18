package com.example.netfluxbe.repository;

import com.example.netfluxbe.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
