package com.example.netfluxbe.entity;

import java.io.Serializable;
import java.util.Objects;

public class MovieCategoryId implements Serializable {
    private String movieId;
    private String categoryId;

    public MovieCategoryId() {
    }

    public MovieCategoryId(String movieId, String categoryId) {
        this.movieId = movieId;
        this.categoryId = categoryId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCategoryId)) return false;
        MovieCategoryId that = (MovieCategoryId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, categoryId);
    }
}
