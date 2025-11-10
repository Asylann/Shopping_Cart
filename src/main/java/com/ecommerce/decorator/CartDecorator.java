package com.ecommerce.decorator;

public abstract class CartDecorator implements CartItem {
    protected CartItem cartItem;

    public CartDecorator(CartItem cartItem) {
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