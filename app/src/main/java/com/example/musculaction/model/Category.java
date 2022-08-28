package com.example.musculaction.model;

public class Category {
    private int id;
    private String title;
    private int image;

    public Category(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
