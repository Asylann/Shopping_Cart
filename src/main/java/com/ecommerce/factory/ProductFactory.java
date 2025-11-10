package com.ecommerce.factory;

public class ProductFactory {
    public Product createProduct(String type, String name, double price) {
        return switch (type.toLowerCase()) {
            case "electronics" -> new Electronics(name, price);
            case "clothing" -> new Clothing(name, price);
            case "book" -> new Book(name, price);
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        };
    }
}