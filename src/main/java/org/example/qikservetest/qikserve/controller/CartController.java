package org.example.qikservetest.qikserve.controller;

import org.example.qikservetest.qikserve.domain.model.CartDetails;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.ProductWithPromotion;
import org.example.qikservetest.qikserve.domain.model.Promotion;
import org.example.qikservetest.qikserve.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) throws IOException {
        Product product = this.cartService.fetchProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws IOException {
        List<Product> products = this.cartService.fetchProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/details")
    public CartDetails getCartDetails() throws IOException {
        return cartService.fetchCartDetails();
    }
}
