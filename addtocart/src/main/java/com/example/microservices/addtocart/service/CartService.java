package com.example.microservices.addtocart.service;

import com.example.microservices.addtocart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private final Map<String, Map<String, CartItem>> cartStore = new HashMap<>();

    public void addToCart(String userId, String productId, int quantity) {

        cartStore.putIfAbsent(userId, new HashMap<>());
        Map<String, CartItem> userCart = cartStore.getOrDefault(userId, new HashMap<>());
        userCart.putIfAbsent(productId, new CartItem(productId, 0));
        userCart.get(productId).setQuantity(userCart.get(productId).getQuantity() + quantity);

    }

    public Map<String, CartItem> viewCart(
            String userId) {

        return cartStore.getOrDefault(userId, new HashMap<>());
    }
}
