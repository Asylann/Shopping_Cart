package com.ecommerce.strategy;

/**
 * Percentage-based discount implementation.
 * Follows Single Responsibility Principle - only handles percentage discount calculation.
 */
public class PercentageDiscount implements DiscountStrategy {
    private final double percentage;

    public PercentageDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return originalPrice * (1 - percentage / 100);
    }
}