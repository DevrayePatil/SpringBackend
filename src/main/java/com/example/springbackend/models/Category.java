package com.example.springbackend.models;

import lombok.Getter;
import lombok.Setter;


public class Category {
    private long id;
    private String title;

    public Category() {
    }

    public Category(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
