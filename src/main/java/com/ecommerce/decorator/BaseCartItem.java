package com.ecommerce.decorator;

public class BaseCartItem implements CartItem {
    private final String description;
    private final double cost;

    public BaseCartItem(String description, double cost) {
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