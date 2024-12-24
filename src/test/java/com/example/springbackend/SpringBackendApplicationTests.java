package com.example.springbackend;

import com.example.springbackend.models.Category;
import com.example.springbackend.models.Product;
import com.example.springbackend.repository.CategoryRepository;
import com.example.springbackend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBackendApplicationTests {


	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testQuery() {
		List<Product> products =  productRepository.getProductsByCategory(1L);
		for (Product product: products) {
			System.out.println(product);
		}
	}

	@Test
	void testFetchTypes() {
		Category category = categoryRepository.findById(1L).get();

		System.out.println("Category Id: " + category.getId());
		System.out.println("Got categories");

//		List<Product> categoryProducts = category.getProducts();
//
//		System.out.println("Products Size: " + categoryProducts.size());
//		System.out.println("Got Products");
	}
}
