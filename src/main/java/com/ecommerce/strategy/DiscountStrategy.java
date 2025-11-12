package com.ecommerce.strategy;

/**
 * Strategy interface for applying discounts.
 * Follows Strategy Pattern and Dependency Inversion Principle.
 * Allows different discount algorithms to be interchanged at runtime.
 */
public interface DiscountStrategy {
    /**
     * Apply discount to the given price.
     * @param originalPrice the original price
     * @return the discounted price
     * @throws IllegalArgumentException if price is negative
     */
    double applyDiscount(double originalPrice);
}