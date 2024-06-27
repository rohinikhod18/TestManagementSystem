package com.bnt.entity;

public class CategoryDTO {

    private Long categoryId;
    private String title;
    private String description;

    public CategoryDTO() {
        // Default constructor
    }

    public CategoryDTO(Long categoryId, String title, String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
