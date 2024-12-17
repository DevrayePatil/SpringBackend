package com.example.springbackend.controller;

import com.example.springbackend.dto.ErrorDto;
import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Product;
import com.example.springbackend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getImage(), product.getPrice(),
                product.getCategory().getTitle());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotFoundException {

        Product p = productService.getProduct(id);
        return new ResponseEntity<>(p, OK);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    public void updateProduct(Product product) {

    }

    public  void deleteProduct(long id) {

    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ErrorDto> handelProductNotFound(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());

        return  new ResponseEntity<>(errorDto, NOT_FOUND);
    }
}
