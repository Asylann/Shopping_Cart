package com.ecommerce.observer;

import java.util.*;

/**
 * Product inventory that manages stock and price updates.
 * Follows Single Responsibility Principle - only manages inventory state and notifications.
 * Follows Dependency Inversion Principle - depends on observer interfaces, not concrete implementations.
 */
public class ProductInventory {
    private final Map<String, Integer> stock;
    private final Map<String, Double> prices;
    private final List<StockUpdate> stockObservers;
    private final List<PriceUpdate> priceObservers;

    public ProductInventory() {
        this.stock = new HashMap<>();
        this.prices = new HashMap<>();
        this.stockObservers = new ArrayList<>();
        this.priceObservers = new ArrayList<>();
    }

    public void addStockObserver(StockUpdate observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Stock observer cannot be null");
        }
        stockObservers.add(observer);
    }

    public void removeStockObserver(StockUpdate observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Stock observer cannot be null");
        }
        stockObservers.remove(observer);
    }

    public void addPriceObserver(PriceUpdate observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Price observer cannot be null");
        }
        priceObservers.add(observer);
    }

    public void removePriceObserver(PriceUpdate observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Price observer cannot be null");
        }
        priceObservers.remove(observer);
    }

    public void updateStock(String productName, int quantity) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        stock.put(productName, quantity);
        notifyStockObservers(productName, quantity);
    }

    public void updatePrice(String productName, double newPrice) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Double oldPrice = prices.get(productName);
        prices.put(productName, newPrice);
        notifyPriceObservers(productName, newPrice, oldPrice != null ? oldPrice : newPrice);
    }

    public int getStock(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        return stock.getOrDefault(productName, 0);
    }

    public double getPrice(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        return prices.getOrDefault(productName, 0.0);
    }

    private void notifyStockObservers(String productName, int quantity) {
        for (StockUpdate observer : stockObservers) {
            observer.onStockUpdate(productName, quantity);
        }
    }

    private void notifyPriceObservers(String productName, double newPrice, double oldPrice) {
        for (PriceUpdate observer : priceObservers) {
            observer.onPriceUpdate(productName, newPrice, oldPrice);
        }
    }
}