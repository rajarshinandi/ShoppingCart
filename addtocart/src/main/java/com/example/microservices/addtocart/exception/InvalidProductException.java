package com.example.microservices.addtocart.exception;

//Why runtime exception?
// Because it is an unchecked exception, which means that it does not need to
// be declared in a method's throws clause and can be thrown at any point during
// the execution of the program. This allows for more flexibility in handling
// exceptions, as it can be caught and handled at a higher level in the application
// without the need for explicit declaration. Additionally,
// runtime exceptions are typically used for programming errors or unexpected
// conditions that are not expected to be recoverable, which is often the case with
// invalid product scenarios in a shopping cart application.

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }

}
