package com.ecommerce.observer;

/**
 * Handles price update notifications for customers.
 * Separated concern following Single Responsibility Principle.
 */
public class PriceUpdateNotifier implements PriceUpdate {
    private final Customer customer;

    public PriceUpdateNotifier(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void onPriceUpdate(String productName, double newPrice, double oldPrice) {
        if (newPrice < oldPrice) {
            String message = productName + " - Price dropped from $" + oldPrice + " to $" + newPrice;
            // Notify through customer's notification system
            System.out.println("Notification for " + customer.getName() + ": " + message);
        }
    }
}
