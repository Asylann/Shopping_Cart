package com.ecommerce.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Director for constructing products using various strategies.
 * Follows Open/Closed Principle - can add new construction strategies without modifying this class.
 */
public class ProductDirector {
    private final Map<String, ProductConstructionStrategy> strategies;

    public ProductDirector() {
        this.strategies = new HashMap<>();
        // Register default strategies
        registerStrategy("smartphone", new SmartphoneConstructionStrategy());
        registerStrategy("laptop", new LaptopConstructionStrategy());
    }

    /**
     * Register a new product construction strategy.
     * Allows extending functionality without modifying the director.
     */
    public void registerStrategy(String productType, ProductConstructionStrategy strategy) {
        strategies.put(productType.toLowerCase(), strategy);
    }

    /**
     * Create a product using the registered strategy.
     */
    public Product createProduct(String productType) {
        ProductConstructionStrategy strategy = strategies.get(productType.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown product type: " + productType);
        }
        return strategy.construct();
    }

    // Convenience methods for common products
    public Product createSmartphone() {
        return createProduct("smartphone");
    }

    public Product createLaptop() {
        return createProduct("laptop");
    }
}