package org.example.qikservetest.qikserve.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Promotion {
    public enum PromotionType {
        QTY_BASED_PRICE_OVERRIDE, FLAT_PERCENT, BUY_X_GET_Y_FREE
    }

    @Id
    private String productId;
    private PromotionType promotionType;
    private int discount;
    private int requiredQty;
    private int freeQty;

    public Promotion(String productId, PromotionType promotionType, int discount, int requiredQty, int freeQty) {
        this.productId = productId;
        this.promotionType = promotionType;
        this.discount = discount;
        this.requiredQty = requiredQty;
        this.freeQty = freeQty;
    }

    public Promotion(){

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(int requiredQty) {
        this.requiredQty = requiredQty;
    }

    public int getFreeQty() {
        return freeQty;
    }

    public void setFreeQty(int freeQty) {
        this.freeQty = freeQty;
    }
}
