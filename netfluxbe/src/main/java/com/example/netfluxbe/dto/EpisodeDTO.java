package com.example.netfluxbe.dto;

public class EpisodeDTO {
    private Long episodeId;
    private String name;




    public EpisodeDTO(Long  episodeId, String name) {
        this.episodeId = episodeId;
        this.name = name;
    }

    public EpisodeDTO() {

    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
