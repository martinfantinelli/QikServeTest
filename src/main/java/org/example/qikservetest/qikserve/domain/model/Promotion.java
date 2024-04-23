package org.example.qikservetest.qikserve.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Promotion {
    public enum PromotionType {
        QTY_BASED_PRICE_OVERRIDE, FLAT_PERCENT, BUY_X_GET_Y_FREE
    }

    @Id
    private String id;
    private PromotionType type;
    private int discount;
    private int required_qty;
    private int free_qty;
    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;

    public Promotion(String id, PromotionType promotionType, int discount, int requiredQty, int freeQty, Product product) {
        this.id = id;
        this.type = promotionType;
        this.discount = discount;
        this.required_qty = requiredQty;
        this.free_qty = freeQty;
        this.product = product;
    }

    public Promotion(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRequired_qty() {
        return required_qty;
    }

    public void setRequired_qty(int required_qty) {
        this.required_qty = required_qty;
    }

    public int getFree_qty() {
        return free_qty;
    }

    public void setFree_qty(int free_qty) {
        this.free_qty = free_qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
