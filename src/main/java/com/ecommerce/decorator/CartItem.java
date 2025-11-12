package com.ecommerce.decorator;

/**
 * CartItem interface representing items that can be added to a shopping cart.
 * Follows Interface Segregation Principle - focused interface for cart items.
 */
public interface CartItem {
    String getDescription();
    double getCost();
}