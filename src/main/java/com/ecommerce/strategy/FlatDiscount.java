package com.ecommerce.strategy;

public class FlatDiscount implements DiscountStrategy {
    private final double amount;

    public FlatDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return Math.max(0, originalPrice - amount);
    }
}