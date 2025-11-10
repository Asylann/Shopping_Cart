package com.ecommerce.decorator;

public class WarrantyDecorator extends CartDecorator {
    public WarrantyDecorator(CartItem cartItem) {
        super(cartItem);
    }

    @Override
    public String getDescription() {
        return cartItem.getDescription() + " + Extended Warranty";
    }

    @Override
    public double getCost() {
        return cartItem.getCost() + 29.99; // Warranty costs $29.99
    }
}