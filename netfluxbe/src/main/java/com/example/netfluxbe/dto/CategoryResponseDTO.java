package com.example.netfluxbe.dto;

public class CategoryResponseDTO {
    private String id;
    private String slug;
    private String name;
    private Long count;

    public CategoryResponseDTO(String id, String slug, String name, Long count) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
