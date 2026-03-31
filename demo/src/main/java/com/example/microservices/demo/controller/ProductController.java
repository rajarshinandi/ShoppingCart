package com.example.microservices.demo.controller;


import com.example.microservices.demo.entity.Product;
import com.example.microservices.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        // First we save the product to the database using the productRepository's save method.
        // This will return the saved product, which includes the generated productId.
        Product savedProduct = productRepository.save(product);

        Map<String, Object> response = Map.of(
                "message", "Product created successfully",
                "productId", savedProduct.getProductId()
        );

        // We return a ResponseEntity with a status of 201 (Created) and the response body containing the success message and the generated productId.
        return ResponseEntity.status(201).body(response);
    }

}
