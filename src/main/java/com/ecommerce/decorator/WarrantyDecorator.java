package com.ecommerce.decorator;

/**
 * Decorator for adding extended warranty to cart items.
 * Follows Decorator Pattern and Open/Closed Principle.
 * Can be stacked with other decorators without modifying original item.
 */
public class WarrantyDecorator extends CartDecorator {
    private static final double WARRANTY_COST = 29.99;

    public WarrantyDecorator(CartItem cartItem) {
        super(cartItem);
    }

    @Override
    public String getDescription() {
        return cartItem.getDescription() + " + Extended Warranty";
    }

    @Override
    public double getCost() {
        return cartItem.getCost() + WARRANTY_COST;
    }
}