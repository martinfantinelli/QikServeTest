package org.example.qikservetest.qikserve.domain;

import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.model.Promotion;

public class ProductWithPromotion {
    private Product product;
    private Promotion promotion;
    private int quantity;

    public ProductWithPromotion(Product product, Promotion promotion, int quantity) {
        this.product = product;
        this.promotion = promotion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
