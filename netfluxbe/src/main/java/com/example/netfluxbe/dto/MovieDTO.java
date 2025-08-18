package com.example.netfluxbe.dto;

import java.util.List;

public class MovieDTO {
    private String id;
    private String title;
    private int year;
    private boolean isSeries;
    private Integer episodesCount; // null neu phim le
    private Integer durationMinutes; // null neu phim bo
    private List<String> categories;
    private String poster;
    private Float rating;
    private String description;
    private List<EpisodeDTO> episodes;


    public MovieDTO(String id, String title, int year, boolean isSeries, Integer episodesCount, Integer durationMinutes,
                    List<String> categories, String poster, Float rating, String description, List<EpisodeDTO> episodes) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.isSeries = isSeries;
        this.episodesCount = episodesCount;
        this.durationMinutes = durationMinutes;
        this.categories = categories;
        this.poster = poster;
        this.rating = rating;
        this.description = description;
        this.episodes = episodes;
    }

    public MovieDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSeries() {
        return isSeries;
    }

    public void setSeries(boolean series) {
        isSeries = series;
    }

    public Integer getEpisodesCount() {
        return episodesCount;
    }

    public void setEpisodesCount(Integer episodesCount) {
        this.episodesCount = episodesCount;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EpisodeDTO> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeDTO> episodes) {
        this.episodes = episodes;
    }
}
