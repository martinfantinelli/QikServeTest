package org.example.qikservetest.qikserve.services;

import org.example.qikservetest.qikserve.domain.model.CartDetails;
import org.example.qikservetest.qikserve.domain.model.CartItem;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.model.Promotion;
import org.example.qikservetest.qikserve.client.WiremockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CartService {
    private final WiremockClient wiremockClient;

    @Autowired
    public CartService(WiremockClient wiremockClient) {
        this.wiremockClient = wiremockClient;
    }

    public int applyPromotion(Product product, Promotion promotion, int quantity) {
        int finalPrice = product.getPrice();

        if (promotion != null) {
            switch (promotion.getType()) {
                case FLAT_PERCENT:
                    int discountPercentage = promotion.getDiscount();
                    int discountAmount = (product.getPrice() * discountPercentage / 100) * quantity;
                    finalPrice -= discountAmount;
                    break;

                case BUY_X_GET_Y_FREE:
                    int x = promotion.getRequired_qty();
                    int y = promotion.getFree_qty();

                    int cycles = quantity / x;
                    int freeQuantity = cycles * y;

                    finalPrice -= freeQuantity * product.getPrice();
                    break;

                case QTY_BASED_PRICE_OVERRIDE:
                    this.applyQtyBasedPriceOverride(product, promotion, quantity);
                    break;

                default:
                    break;
            }
        }

        return finalPrice;
    }

    public int applyQtyBasedPriceOverride(Product product, Promotion promotion, int quantity) {
        int requiredQty = promotion.getRequired_qty();
        int specialPrice = promotion.getDiscount();

        int totalPrice = 0;

        int numSpecialPrice = quantity / requiredQty;

        int remainingQty = quantity % requiredQty;

        totalPrice += numSpecialPrice * specialPrice;

        totalPrice += remainingQty * product.getPrice();

        return totalPrice;
    }

    public int calculateTotalPrice() throws IOException {
        CartDetails cartDetails = wiremockClient.fetchCartDetails();

        int totalPrice = 0;
        for (CartItem item : cartDetails.getItems()) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public int calculateTotalSavings() throws IOException {
        CartDetails cartDetails = wiremockClient.fetchCartDetails();

        return cartDetails.getTotalSavings();
    }

    public List<Product> fetchProducts() throws IOException {
        return this.wiremockClient.fetchProducts();
    }

    public Product fetchProductById(String productId) throws IOException {
        return this.wiremockClient.fetchProductById(productId);
    }

    public Promotion fetchPromotionByPromotionId(String promotionId) throws IOException {
        return this.wiremockClient.fetchPromotionByProductId(promotionId);
    }

    public CartDetails fetchCartDetails() throws IOException {
        return wiremockClient.fetchCartDetails();
    }

    public void addItem(String productId, int quantity) throws IOException {
        wiremockClient.addItem(productId, quantity);
    }
}
