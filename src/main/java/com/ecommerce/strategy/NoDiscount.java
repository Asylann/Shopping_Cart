package com.ecommerce.strategy;

/**
 * No discount implementation (null object pattern).
 * Follows Single Responsibility Principle - represents the absence of a discount.
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return originalPrice;
    }
}