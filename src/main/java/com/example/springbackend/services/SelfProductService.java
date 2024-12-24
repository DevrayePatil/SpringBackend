package com.example.springbackend.services;

import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Category;
import com.example.springbackend.models.Product;
import com.example.springbackend.repository.CategoryRepository;
import com.example.springbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImage(product.getImage());

        String categoryTitle = product.getCategory().getTitle();
        Category currrentCategory = categoryRepository.findByTitle(categoryTitle);

        if (currrentCategory == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            Category newRow = categoryRepository.save(newCategory);
            newProduct.setCategory(newRow);
        }
        else {
            newProduct.setCategory(currrentCategory);
        }

        return productRepository.save(newProduct);
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found with id: " + id);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("Got all Products");
        return products;
    }

    @Override
    public Product updateProduct(long id, Product product) throws ProductNotFoundException{
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updateProduct = existingProduct.get();
            if (product.getTitle() != null)
                updateProduct.setTitle(product.getTitle());
            if (product.getDescription() != null)
                updateProduct.setDescription(product.getDescription());
            if (product.getCategory() != null) {
                String categoryTitle = product.getCategory().getTitle();
                Category currrentCategory = categoryRepository.findByTitle(categoryTitle);
                if (currrentCategory == null) {
                    Category newCategory = new Category();
                    newCategory.setTitle(categoryTitle);
                    Category newRow = categoryRepository.save(newCategory);
                    updateProduct.setCategory(newRow);
                }
                else {
                    updateProduct.setCategory(currrentCategory);
                }
            }
            if (product.getPrice() != null)
                updateProduct.setPrice(product.getPrice());
            if (product.getImage() != null)
                updateProduct.setImage(product.getImage());

            return productRepository.save(updateProduct);
        }
        throw new ProductNotFoundException("Product not found with id: " + id);
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            productRepository.delete(product.get());
        else
            throw new ProductNotFoundException("Product not found with id: " + id);
    }
}
