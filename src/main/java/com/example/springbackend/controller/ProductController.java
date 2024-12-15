package com.example.springbackend.controller;

import com.example.springbackend.models.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @PostMapping("/products")
    public void addProduct() {

    }

    public Product getProduct(long id) {

        return null;
    }

    public void updateProduct(Product product) {

    }

    public  void deleteProduct(long id) {

    }
}
