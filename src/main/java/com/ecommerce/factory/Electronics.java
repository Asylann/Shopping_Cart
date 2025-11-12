package com.ecommerce.factory;

/**
 * Electronics product implementation.
 * Follows Single Responsibility Principle - only represents an electronics product.
 */
public class Electronics implements Product {
    private final String name;
    private final double price;
    
    public Electronics(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return "Electronics: " + name;
    }
}