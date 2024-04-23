package org.example.qikservetest.qikserve.controller;

import org.example.qikservetest.qikserve.domain.model.CartDetails;
import org.example.qikservetest.qikserve.domain.model.ItemRequest;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/addItem")
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody ItemRequest itemRequest) {
        try {
            cartService.addItem(itemRequest.getProductId(), itemRequest.getQuantity());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Item added to cart successfully");
            response.put("productId", itemRequest.getProductId());
            response.put("quantity", itemRequest.getQuantity());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "" + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
