package com.example.springbackend.controller;

import com.example.springbackend.dto.ErrorDto;
import com.example.springbackend.dto.ResponseDto;
import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Product;
import com.example.springbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        System.out.println("Product controller");
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException{
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseDto("Product deleted Successfully"), OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ErrorDto> handelProductNotFound(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return  new ResponseEntity<>(errorDto, NOT_FOUND);
    }
}
