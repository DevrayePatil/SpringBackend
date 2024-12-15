package com.example.springbackend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private long id;
    private String title;

    public Category() {
    }

    public Category(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
