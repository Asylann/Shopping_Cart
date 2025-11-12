package com.ecommerce.factory;

/**
 * Product interface representing a product in the e-commerce system.
 * Follows Interface Segregation Principle - focused on essential product operations.
 */
public interface Product {
    String getName();
    double getPrice();
    String getDescription();
}