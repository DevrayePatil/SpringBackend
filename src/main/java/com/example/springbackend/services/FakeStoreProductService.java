package com.example.springbackend.services;

import com.example.springbackend.dto.FakeStoreProductDto;
import com.example.springbackend.exceptions.ProductNotFoundException;
import com.example.springbackend.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;
    final String fakeStoreUrl= "https://fakestoreapi.com/products/";

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());

        FakeStoreProductDto response = restTemplate.
                postForObject(fakeStoreUrl, fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return response.getProduct();
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        System.out.println("We are in the single product service");
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject(fakeStoreUrl + id,
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
                .getForObject(fakeStoreUrl,
                FakeStoreProductDto[].class);
        System.out.println("From Products service");

        Product[] products = new Product[fakeStoreProductDto.length];

            for (int i = 0; i < products.length; i++) {
            products[i] = fakeStoreProductDto[i].getProduct();
        }
       return List.of(products);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreProductDto dto = restTemplate.
                patchForObject(fakeStoreUrl + id, product, FakeStoreProductDto.class);

        Product updatedProduct = dto.getProduct();
        return updatedProduct;
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        restTemplate.delete(fakeStoreUrl + id);
    }
}
