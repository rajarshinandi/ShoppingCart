# ShoppingCart Microservices - Dockerized Setup

This project demonstrates a **microservices architecture** for a shopping cart system with independently deployable services. It uses **Spring Boot**, **Maven**, and **Docker**, and is designed to be run locally with separate containers for each service.

---

## 1. Project Structure

Each microservice has its own Maven module and folder:

```
ShoppingCart/
├── products/       # Product Service
│   ├── src/
│   ├── pom.xml
├── cart/           # Cart Service
│   ├── src/
│   ├── pom.xml
└── checkout/       # Checkout Service
    ├── src/
    ├── pom.xml
├── docker/         # Universal Dockerfile for all services
│   └── Dockerfile
```

**Key points:**

- Each service has its **own `pom.xml`** and **builds its own JAR**  
- **One universal Dockerfile** is placed in `docker/` for all services  
- Each container maps its internal service port to a local host port for testing  

---

## 2. Maven Build

Build a JAR for each service:

```bash
# Navigate to service folder
cd products

# Build JAR
mvn clean package

# Resulting JAR
target/products-0.0.1-SNAPSHOT.jar
```

**Notes:**

- Avoid `SNAPSHOT` in production; use versioned JARs for proper versioning  
- Example: `products-1.0.0.jar` → tag Docker image `product-service:1.0.0`  

---

## 3. Universal Dockerfile

Place this Dockerfile in `docker/Dockerfile`. It can be used for any service by passing the JAR filename at build time.

```dockerfile
# docker/Dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

ARG JAR_FILE
COPY ../$JAR_FILE app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Key points:**

- `ARG JAR_FILE` allows passing the JAR name during build  
- Dockerfile is **universal** for all services  
- Each container should have **one service only**

---

## 4. Building Docker Images

From the service folder:

```bash
# Navigate to service folder
cd products

# Build Docker image using universal Dockerfile
docker build -f ../docker/Dockerfile --build-arg JAR_FILE=products-0.0.1-SNAPSHOT.jar -t product-service:0.0.1 .
```

**Notes:**

- `-f` points to universal Dockerfile path  
- `-t` tags the image with `name:version`  
- Use proper semantic versioning for rollback capability  

---

## 5. Running Docker Containers

Run the container locally:

```bash
docker run -p 8081:8080 product-service:0.0.1
```

**Explanation:**

- `-p 8081:8080` maps **host port 8081** → **container port 8080**  
- Access the API in browser or Postman:

```http
GET / POST http://localhost:8081/products
```

- Verify container is running:

```bash
docker ps
```

---

## 6. Key Concepts Learned

1. **1 Service → 1 Container**  
2. **Independent Deployability**  
3. **Versioning & Tags**  
4. **Build Context Matters**  
5. **Maintained Base Images**  
6. **Networking**

---

## 7. Next Steps

- Dockerize `cart-service` and `checkout-service` using the **universal Dockerfile**  
- Run all three containers together to simulate **microservices mesh**  
- Configure **cart-service** to call `product-service` internally  
- Optionally, use **docker-compose** to manage all services together  

---

## 8. References

- Docker Official Docs: [https://docs.docker.com/](https://docs.docker.com/)  
- Eclipse Temurin JDK: [https://adoptium.net/](https://adoptium.net/)  
- Spring Boot Docker Guide: [https://spring.io/guides/gs/spring-boot-docker/](https://spring.io/guides/gs/spring-boot-docker/)  

---

**Author:** Rajarshi Nandi  
**Date:** 2026-04-05  
**Purpose:** Local practice for Dockerized microservices with independent deployment

