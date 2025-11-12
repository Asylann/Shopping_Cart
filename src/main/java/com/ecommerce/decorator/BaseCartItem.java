package com.ecommerce.decorator;

/**
 * Base implementation of CartItem.
 * Represents a simple cart item without any decorations.
 * Follows Single Responsibility Principle - only represents a basic cart item.
 */
public class BaseCartItem implements CartItem {
    private final String description;
    private final double cost;

    public BaseCartItem(String description, double cost) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }
}