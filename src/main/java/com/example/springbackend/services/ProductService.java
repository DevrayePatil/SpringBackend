package com.example.springbackend.services;

import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Category;
import com.example.springbackend.models.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(long id, String title, String description, String image, Double price, String category);
    Product getProduct(long id) throws ProductNotFoundException;
    List<Product> getProducts();
    Product updateProduct(Product product);
}
