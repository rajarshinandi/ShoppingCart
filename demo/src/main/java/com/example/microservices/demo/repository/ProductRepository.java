package com.example.microservices.demo.repository;

import com.example.microservices.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * What is Repository?
 * In the context of software development, a Repository is a design pattern that provides an abstraction layer
 * between the data access layer and the business logic layer of an application.
 * It is responsible for managing the persistence of data and providing a clean API for accessing and manipulating that data.
 *
 * What it means in Spring Boot?
 * In Spring Boot, a Repository is typically an interface that extends one of the Spring Data repository interfaces (like JpaRepository, CrudRepository, etc.).
 * This allows you to perform CRUD (Create, Read, Update, Delete) operations on your entities without having to write any boilerplate code.
 * Spring Data will automatically generate the implementation of the repository interface at runtime, allowing you to interact with the database using simple method calls.
 *
 */

// ProductRepository is a repository interface that will be used to perform CRUD operations on the Product entity.
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    @Override
    void deleteById(UUID productId);

    @Override
    boolean existsById(UUID productId);
}
