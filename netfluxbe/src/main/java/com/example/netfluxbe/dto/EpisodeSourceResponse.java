package com.example.netfluxbe.dto;

import java.util.List;

public class EpisodeSourceResponse {
    private int episode;
    private String name;
    private List<SourceDTO> items;

    public EpisodeSourceResponse() {
    }

    public EpisodeSourceResponse(int episode, String name, List<SourceDTO> items) {
        this.episode = episode;
        this.name = name;
        this.items = items;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SourceDTO> getItems() {
        return items;
    }

    public void setItems(List<SourceDTO> items) {
        this.items = items;
    }
}
