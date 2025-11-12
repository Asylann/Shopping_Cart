package com.ecommerce.decorator;

/**
 * Abstract base class for decorators.
 * Follows Liskov Substitution Principle - all decorators can be substituted for CartItem.
 * Follows Open/Closed Principle - new decorators can be added without modifying existing code.
 */
public abstract class CartDecorator implements CartItem {
    protected final CartItem cartItem;

    public CartDecorator(CartItem cartItem) {
        if (cartItem == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }
        this.cartItem = cartItem;
    }

    @Override
    public String getDescription() {
        return cartItem.getDescription();
    }

    @Override
    public double getCost() {
        return cartItem.getCost();
    }
}