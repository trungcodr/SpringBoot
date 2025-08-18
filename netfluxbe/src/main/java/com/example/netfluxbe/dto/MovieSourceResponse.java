package com.example.netfluxbe.dto;

import java.util.List;

public class MovieSourceResponse {
    private List<SourceDTO> items;

    public MovieSourceResponse() {
    }

    public MovieSourceResponse(List<SourceDTO> items) {
        this.items = items;
    }

    public List<SourceDTO> getItems() {
        return items;
    }

    public void setItems(List<SourceDTO> items) {
        this.items = items;
    }
}
