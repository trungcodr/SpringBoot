package com.example.netfluxbe.dto;

public class SourceDTO {
    private String type;
    private String quality;
    private String url;

    public SourceDTO() {
    }

    public SourceDTO(String type, String quality, String url) {
        this.type = type;
        this.quality = quality;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
