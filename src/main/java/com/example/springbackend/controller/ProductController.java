package com.example.springbackend.controller;

import com.example.springbackend.models.Product;
import com.example.springbackend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        Product p = productService.addProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getImage(), product.getPrice(),
                product.getCategory().getTitle());
        return p;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") long id) {

        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    public void updateProduct(Product product) {

    }

    public  void deleteProduct(long id) {

    }
}
