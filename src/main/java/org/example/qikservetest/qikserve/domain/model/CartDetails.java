package org.example.qikservetest.qikserve.domain.model;

import java.util.List;

public class CartDetails {
    private List<CartItem> items;
    private int totalSavings;

    public CartDetails() {}

    public CartDetails(List<CartItem> items, int totalSavings) {
        this.items = items;
        this.totalSavings = totalSavings;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(int totalSavings) {
        this.totalSavings = totalSavings;
    }
}
