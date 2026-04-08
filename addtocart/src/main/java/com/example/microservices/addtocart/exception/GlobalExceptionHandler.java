package com.example.microservices.addtocart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

// GlobalExceptionHandler is a custom exception class that extends RuntimeException.
// It is used to handle exceptions globally in the application. When an exception is thrown, it can be caught and handled by this class, allowing for a centralized way to manage errors and provide consistent error responses to the client.

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method is an exception handler for the ProductNotFoundException.
     * When this exception is thrown, it will be caught by this method, which will
     * then create a response entity with a status of NOT_FOUND (404) and a body containing
     * a message with the exception's message.
     *
     * @param ex
     * @return ResponseEntity<Map<String, String>>
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> error = Map.of(
                "message", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); //Http: 404
    }

    /**
     *
     * This method is an exception handler for the InvalidProductException.
     * When this exception is thrown, it will be caught by this method, which will
     * then create a response entity with a status of BAD_REQUEST (400) and a body containing
     * a message with the exception's message.
     * @param ex
     * @return ResponseEntity<Map<String, String>>
     */
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<Map<String, String>> handleInvalidProduct(InvalidProductException ex) {

        Map<String, String> error = Map.of(
                "message", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); //Http: 400
    }

    /**
     * This method is a generic exception handler that catches any exceptions
     * that are not specifically handled by the other methods in this class.
     *
     * @param ex
     * @return ResponseEntity<Map<String, String>>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {

        Map<String, String> error = Map.of(
                "message", "An unexpected error occurred: " + ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); //Http: 500
    }


}
