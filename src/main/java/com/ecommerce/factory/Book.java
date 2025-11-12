package com.ecommerce.factory;

/**
 * Book product implementation.
 * Follows Single Responsibility Principle - only represents a book product.
 */
public class Book implements Product {
    private final String name;
    private final double price;
    
    public Book(String name, double price) {
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
        return "Book: " + name;
    }
}