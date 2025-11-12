package com.ecommerce.observer;

/**
 * Handles stock update notifications for customers.
 * Separated concern following Single Responsibility Principle.
 */
public class StockUpdateNotifier implements StockUpdate {
    private final Customer customer;

    public StockUpdateNotifier(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void onStockUpdate(String productName, int quantity) {
        if (quantity < 5) {
            String message = productName + " - Low stock alert: " + quantity + " units remaining";
            // Notify through customer's notification system
            System.out.println("Notification for " + customer.getName() + ": " + message);
        }
    }
}
