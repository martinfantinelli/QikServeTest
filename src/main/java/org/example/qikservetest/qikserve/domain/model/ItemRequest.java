package org.example.qikservetest.qikserve.domain.model;

public class ItemRequest {
    private final String productId;
    private final int quantity;

    public ItemRequest(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
