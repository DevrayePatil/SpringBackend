package com.example.springbackend.services;

import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product addProduct(Product product);
    Product getProduct(long id) throws ProductNotFoundException;
    Product updateProduct(long id, Product product) throws ProductNotFoundException;
    void deleteProduct(long id) throws  ProductNotFoundException;
}
