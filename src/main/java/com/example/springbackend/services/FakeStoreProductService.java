package com.example.springbackend.services;

import com.example.springbackend.dto.FakeStoreProductDto;
import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product addProduct(long id, String title, String description,
                              String image, Double price, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate.
                postForObject("https://fakestoreapi.com/products", fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return response.getProduct();
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        System.out.println("We are in the single product service");
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        System.out.println(fakeStoreProductDto);

        return fakeStoreProductDto.getProduct();
    }

    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        System.out.println("From Products service");

        Product[] products = new Product[fakeStoreProductDto.length];

            for (int i = 0; i < products.length; i++) {
            products[i] = fakeStoreProductDto[i].getProduct();
        }
       return List.of(products);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
