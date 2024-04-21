package org.example.qikservetest.qikserve.services;

import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.ProductWithPromotion;
import org.example.qikservetest.qikserve.domain.model.Promotion;
import org.example.qikservetest.qikserve.client.WiremockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CartService {
    private WiremockClient wiremockClient;

    @Autowired
    public CartService(WiremockClient wiremockClient) {
        this.wiremockClient = wiremockClient;
    }

    public int calculateTotalPrice(List<ProductWithPromotion> cartItems) {
        int totalPrice = 0;

        for (ProductWithPromotion item : cartItems) {
            Product product = item.getProduct();
            Promotion promotion = item.getPromotion();
            int quantity = item.getQuantity();

            int finalPrice = applyPromotion(product, promotion, quantity);

            totalPrice += finalPrice;
        }

        return totalPrice;
    }

    public int applyPromotion(Product product, Promotion promotion, int quantity) {
        int finalPrice = product.getPrice();

        if (promotion != null) {
            switch (promotion.getPromotionType()) {
                case FLAT_PERCENT:
                    int discountPercentage = promotion.getDiscount();
                    int discountAmount = (product.getPrice() * discountPercentage / 100) * quantity;
                    finalPrice -= discountAmount;
                    break;

                case BUY_X_GET_Y_FREE:
                    int x = promotion.getRequiredQty();
                    int y = promotion.getFreeQty();

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
        int requiredQty = promotion.getRequiredQty();
        int specialPrice = promotion.getDiscount();

        int totalPrice = 0;

        int numSpecialPrice = quantity / requiredQty;

        int remainingQty = quantity % requiredQty;

        totalPrice += numSpecialPrice * specialPrice;

        totalPrice += remainingQty * product.getPrice();

        return totalPrice;
    }

    public int calculateTotalSavings(List<ProductWithPromotion> cartItems) {
        int totalSavings = 0;

        for (ProductWithPromotion item : cartItems) {
            Product product = item.getProduct();
            Promotion promotion = item.getPromotion();
            int quantity = item.getQuantity();

            if (promotion != null) {
                int originalPrice = product.getPrice();
                int finalPrice = applyPromotion(product, promotion, quantity);

                totalSavings += originalPrice - finalPrice;
            }
        }

        return totalSavings;
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
}
