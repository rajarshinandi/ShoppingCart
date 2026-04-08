package com.example.microservices.addtocart.controller;


import com.example.microservices.addtocart.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.microservices.addtocart.service.CartService;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public Map<String, String> addToCart(
            @RequestParam String productId,
            @RequestParam int quantity,
            @RequestParam String userId
    ) {
        cartService.addToCart(userId, productId, quantity);
        return Map.of("Message","Product added to cart successfully.",
                        "Item details",
                Map.of("userId", userId,
                "productId", productId,
                "quantity", String.valueOf(quantity)).toString());
    }

    @GetMapping("/view")
    public Map<String, Object> viewCartService(
            @RequestParam String userId) {

        Map<String,CartItem> cartItem = cartService.viewCart(userId);
        return Map.of(
                "userId", userId,
                " Items",cartItem
                );
    }

}
