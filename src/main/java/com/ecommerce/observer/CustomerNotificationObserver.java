package com.ecommerce.observer;

/**
 * Interface for handling customer notifications.
 * Follows Interface Segregation Principle by having a single, focused responsibility.
 */
public interface CustomerNotificationObserver {
    void notify(String customerName, String productName, String message);
}
