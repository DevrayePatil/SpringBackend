package com.example.springbackend.models;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Product {
    private long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private Category category;

    public Product() {

    }

    public Product(long id, String title, String description,
                   Double price, String image,
                   Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
