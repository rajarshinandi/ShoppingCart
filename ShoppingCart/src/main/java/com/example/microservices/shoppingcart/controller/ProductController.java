package com.example.microservices.shoppingcart.controller;


import com.example.microservices.shoppingcart.entity.Product;
import com.example.microservices.shoppingcart.repository.ProductRepository;
import com.example.microservices.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

// Why @RestController?
// The @RestController annotation is a convenience annotation that combines @Controller and @ResponseBody.
// It is used to create RESTful web services in Spring Boot.
// When a class is annotated with @RestController, it indicates that the class is a controller where every method returns a domain object instead of a view.
// The @ResponseBody annotation tells Spring to convert the return value of the method into a JSON or XML response, depending on the client's request.
// In this case, the ProductController class is annotated with @RestController, which means that it will handle HTTP requests and return JSON responses containing Product data

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable("productId") UUID productId) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return ResponseEntity.status(404).body("Product not found with id: " + productId);
        }
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct); // Return the created product with a 201 status code
    }
}
