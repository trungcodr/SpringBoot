package com.example.netfluxbe.dto;

import java.util.List;

public class SeriesSourceResponse {
    private List<EpisodeSourceResponse> episodes;

    public SeriesSourceResponse() {
    }

    public SeriesSourceResponse(List<EpisodeSourceResponse> episodes) {
        this.episodes = episodes;
    }

    public List<EpisodeSourceResponse> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeSourceResponse> episodes) {
        this.episodes = episodes;
    }
}
