package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.model.Cart;
import com.ayrton.AgroGestor.model.CartItem;
import com.ayrton.AgroGestor.model.Sale;
import com.ayrton.AgroGestor.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Cart cart = cartService.getOrCreateCart(userId);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable String userId, @RequestBody CartItem cartItem) {
        Cart updatedCart = cartService.addItemToCart(userId, cartItem);
        return ResponseEntity.ok(updatedCart);
    }

    // DELETE /api/cart/{userId}/item/{productId}
    @DeleteMapping("/{userId}/item/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String userId, @PathVariable String productId) {
        Cart updatedCart = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    // POST /api/cart/{userId}/checkout?buyerName=Fulano
    @PostMapping("/{userId}/checkout")
    public ResponseEntity<List<Sale>> checkout(@PathVariable String userId, @RequestParam String buyerName) {
        List<Sale> sales = cartService.checkout(userId, buyerName);
        return ResponseEntity.ok(sales);
    }
}

