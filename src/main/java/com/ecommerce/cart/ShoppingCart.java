package com.ecommerce.cart;

import com.ecommerce.decorator.CartItem;
import com.ecommerce.strategy.DiscountStrategy;
import com.ecommerce.strategy.NoDiscount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Shopping cart implementation.
 * Follows Dependency Inversion Principle - depends on DiscountStrategy abstraction, not concrete implementations.
 * Follows Single Responsibility Principle - only manages cart items and applies discounts.
 */
public class ShoppingCart {
    private final List<CartItem> items;
    private DiscountStrategy discountStrategy;

    public ShoppingCart() {
        this(new NoDiscount());
    }

    public ShoppingCart(DiscountStrategy initialStrategy) {
        this.items = new ArrayList<>();
        this.discountStrategy = Objects.requireNonNull(initialStrategy, "Discount strategy cannot be null");
    }

    public void addItem(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }
        items.add(item);
    }

    public void removeItem(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = Objects.requireNonNull(strategy, "Discount strategy cannot be null");
    }

    public double calculateTotal() {
        double total = items.stream().mapToDouble(CartItem::getCost).sum();
        return discountStrategy.applyDiscount(total);
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }
}