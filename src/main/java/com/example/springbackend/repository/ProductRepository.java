package com.example.springbackend.repository;

import com.example.springbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> getProductsByCategory(@Param("categoryId") long categoryId);

    @Query("select p from Product p")
    List<Product> getProducts();


}
