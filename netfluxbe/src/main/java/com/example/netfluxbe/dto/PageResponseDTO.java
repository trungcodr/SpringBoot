package com.example.netfluxbe.dto;

import java.util.List;

public class PageResponseDTO<T> {
    private int page;
    private int size;
    private Long total;
    private List<T> items;

    public PageResponseDTO() {
    }

    public PageResponseDTO(int page, int size, Long total, List<T> items) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
