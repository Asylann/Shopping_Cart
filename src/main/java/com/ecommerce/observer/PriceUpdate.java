package com.ecommerce.observer;

/**
 * Observer interface for price updates.
 * Follows Interface Segregation Principle - focused interface for price observers only.
 */
public interface PriceUpdate {
    void onPriceUpdate(String productName, double newPrice, double oldPrice);
}