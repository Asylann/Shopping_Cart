package com.ecommerce.decorator;

/**
 * Decorator for adding gift wrapping to cart items.
 * Follows Decorator Pattern and Open/Closed Principle.
 * Can be stacked with other decorators without modifying original item.
 */
public class GiftWrapDecorator extends CartDecorator {
    private static final double GIFT_WRAP_COST = 5.00;

    public GiftWrapDecorator(CartItem cartItem) {
        super(cartItem);
    }

    @Override
    public String getDescription() {
        return cartItem.getDescription() + " + Gift Wrap";
    }

    @Override
    public double getCost() {
        return cartItem.getCost() + GIFT_WRAP_COST;
    }
}