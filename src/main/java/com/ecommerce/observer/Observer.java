package com.ecommerce.observer;

/**
 * Observer interface for general observation.
 * Follows Interface Segregation Principle - focused interface for observers.
 */
public interface Observer {
    void update(String productName, String updateType, String message);
}