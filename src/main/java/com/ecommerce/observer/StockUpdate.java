package com.ecommerce.observer;

/**
 * Observer interface for stock updates.
 * Follows Interface Segregation Principle - focused interface for stock observers only.
 */
public interface StockUpdate {
    void onStockUpdate(String productName, int quantity);
}