package com.ecommerce.decorator;

public class GiftWrapDecorator extends CartDecorator {
    public GiftWrapDecorator(CartItem cartItem) {
        super(cartItem);
    }

    @Override
    public String getDescription() {
        return cartItem.getDescription() + " + Gift Wrap";
    }

    @Override
    public double getCost() {
        return cartItem.getCost() + 5.00; // Gift wrap costs $5
    }
}