package com.ecommerce.observer;

public class Customer implements StockUpdate, PriceUpdate {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void onStockUpdate(String productName, int quantity) {
        if (quantity < 5) {
            System.out.println("Notification for " + name + ": " + productName + " - Low stock alert: " + quantity + " units remaining");
        }
    }

    @Override
    public void onPriceUpdate(String productName, double newPrice, double oldPrice) {
        if (newPrice < oldPrice) {
            System.out.println("Notification for " + name + ": " + productName + " - Price dropped from $" + oldPrice + " to $" + newPrice);
        }
    }
}