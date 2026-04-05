package com.example.microservices.shoppingcart.service;

import com.example.microservices.shoppingcart.entity.Product;
import com.example.microservices.shoppingcart.exception.InvalidProductException;
import com.example.microservices.shoppingcart.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is responsible for creating a new product.
     * It takes a Product object as input, checks if the price is negative,
     * and if it is, it throws an InvalidProductException with a message indicating
     * that the price cannot be negative.
     * If the price is valid, it saves the product to the database
     * using the productRepository's save method and returns the saved product.
     *
     * @param product
     * @return Product
     */
    public Product createProduct(Product product) {

        Double price = product.getPrice();
        if(price == null || price <= 0.0) {
            throw new InvalidProductException("Price is mandatory and cannot be zero or negative");
        }
        productRepository.save(product);
        return product;
    }

}
