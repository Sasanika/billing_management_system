package com.billing.management_system;

public class OrderItems {
    private String orderId;
    private String itemName;
    private int quantity;
    private double totalPrice;

    public OrderItems(String orderId, String itemName, int quantity, double totalPrice) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return orderId + " - " + itemName + " - " + quantity + " - " + totalPrice;
    }
}
