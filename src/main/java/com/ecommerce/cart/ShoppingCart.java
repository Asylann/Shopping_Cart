package com.ecommerce.cart;

import com.ecommerce.decorator.CartItem;
import com.ecommerce.strategy.DiscountStrategy;
import com.ecommerce.strategy.NoDiscount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items;
    private DiscountStrategy discountStrategy;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.discountStrategy = new NoDiscount();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double calculateTotal() {
        double total = items.stream().mapToDouble(CartItem::getCost).sum();
        return discountStrategy.applyDiscount(total);
    }
}