package com.example.microservices.addtocart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {

    private String productId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
