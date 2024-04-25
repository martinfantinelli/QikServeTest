package org.example.qikservetest.qikserve;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.qikservetest.qikserve.client.WiremockClient;
import org.example.qikservetest.qikserve.domain.model.CartDetails;
import org.example.qikservetest.qikserve.domain.model.CartItem;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.model.Promotion;
import org.example.qikservetest.qikserve.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartServiceTest {
    private CartService cartService;
    private WiremockClient wiremockClient;

    @BeforeEach
    public void setUp() {
        wiremockClient = mock(WiremockClient.class);

        cartService = new CartService(wiremockClient);
    }

    @Test
    public void testAddItem() throws IOException {
        String productId = "PWWe3w1SDU";
        int quantity = 5;

        cartService.addItem(productId, quantity);

        verify(wiremockClient).addItem(productId, quantity);
    }

    @Test
    public void testFetchProducts() throws IOException {

        List<Promotion> promotions = new ArrayList<>();

        var productForPromotion = new Product("productId1", "Product 1", 1000, promotions);

        promotions.add(new Promotion("promoId1", Promotion.PromotionType.FLAT_PERCENT, 20, 2, 1, productForPromotion));

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("productId1", "Product 1", 1000, promotions));
        expectedProducts.add(new Product("productId2", "Product 2", 2000, promotions));

        when(wiremockClient.fetchProducts()).thenReturn(expectedProducts);

        List<Product> products = cartService.fetchProducts();

        verify(wiremockClient).fetchProducts();

        assertEquals(expectedProducts, products);
    }

    @Test
    public void testFetchProductById() throws IOException {
        List<Promotion> productPromotions = new ArrayList<>();
        Product expectedProduct = new Product("productId1", "Product 1", 1000, productPromotions);

        Promotion expectedPromotion = new Promotion("promotionId1", Promotion.PromotionType.FLAT_PERCENT, 10, 2, 1, expectedProduct);

        String productId1 = "productId1";
        when(wiremockClient.fetchProductById(productId1)).thenReturn(expectedProduct);

        Product product = cartService.fetchProductById(productId1);

        verify(wiremockClient).fetchProductById(productId1);

        assertEquals(expectedProduct, product);
    }

    @Test
    public void testFetchCartDetails() throws IOException {
        List<CartItem> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(new CartItem("productId1", "Product 1", 2, 1000, 2000, 100));
        expectedCartItems.add(new CartItem("productId2", "Product 2", 3, 1500, 4500, 200));

        CartDetails expectedCartDetails = new CartDetails(expectedCartItems, 300);

        when(wiremockClient.fetchCartDetails()).thenReturn(expectedCartDetails);

        CartDetails cartDetails = cartService.fetchCartDetails();

        verify(wiremockClient).fetchCartDetails();

        assertEquals(expectedCartDetails, cartDetails);
    }

}
