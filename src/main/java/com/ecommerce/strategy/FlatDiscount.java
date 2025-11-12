package com.ecommerce.strategy;

/**
 * Flat amount discount implementation.
 * Follows Single Responsibility Principle - only handles flat discount calculation.
 */
public class FlatDiscount implements DiscountStrategy {
    private final double amount;

    public FlatDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return Math.max(0, originalPrice - amount);
    }
}