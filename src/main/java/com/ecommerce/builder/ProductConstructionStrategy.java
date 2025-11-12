package com.ecommerce.builder;

/**
 * Interface for product construction strategies.
 * Follows Open/Closed Principle - open for extension, closed for modification.
 */
public interface ProductConstructionStrategy {
    Product construct();
}
