package com.ecommerce.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Factory for creating products.
 * Follows Open/Closed Principle - can register new product types without modification.
 * Follows Dependency Inversion Principle - depends on Product interface, not concrete types.
 */
public class ProductFactory {
    private final Map<String, BiFunction<String, Double, Product>> creators;

    public ProductFactory() {
        this.creators = new HashMap<>();
        registerDefaultCreators();
    }

    private void registerDefaultCreators() {
        creators.put("electronics", Electronics::new);
        creators.put("clothing", Clothing::new);
        creators.put("book", Book::new);
    }

    /**
     * Register a new product creator for a type.
     * Allows extending without modifying the factory.
     */
    public void registerProductType(String type, BiFunction<String, Double, Product> creator) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Product type cannot be null or blank");
        }
        if (creator == null) {
            throw new IllegalArgumentException("Creator cannot be null");
        }
        creators.put(type.toLowerCase(), creator);
    }

    /**
     * Create a product of the specified type.
     */
    public Product createProduct(String type, String name, double price) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Product type cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        BiFunction<String, Double, Product> creator = creators.get(type.toLowerCase());
        if (creator == null) {
            throw new IllegalArgumentException("Unknown product type: " + type);
        }
        return creator.apply(name, price);
    }
}