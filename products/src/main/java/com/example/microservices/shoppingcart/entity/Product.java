package com.example.microservices.shoppingcart.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Why @Entity?
 * The @Entity annotation is used to specify that the class is an entity and is mapped to a database table.
 * It is a part of the Java Persistence API (JPA) and is used to indicate that the class should be persisted to the database.
 * When a class is annotated with @Entity, it tells the JPA provider (like Hibernate) to create a corresponding table in the database
 * and to manage the persistence of instances of that class.

 * Why @Id?
 * The @Id annotation is used to specify the primary key of an entity.
 * It is a required annotation for any entity class, as it indicates which field is the unique identifier for instances of the entity.
 * The field annotated with @Id will be used to uniquely identify each record in the database table corresponding to the entity.
 * In this case, the productId field is annotated with @Id, indicating that it is the primary key for the Product entity.

 */

@Entity
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
public class Product {

    @Id
    @GeneratedValue
    private UUID productId;

    private String name;

    @NotNull
    @Positive
    private Double price;

    private String description;

    public Object getProductId() {
        return productId;
    }
}
