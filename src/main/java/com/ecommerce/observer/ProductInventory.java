package com.ecommerce.observer;

import java.util.*;

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
        stockObservers.add(observer);
    }

    public void removeStockObserver(StockUpdate observer) {
        stockObservers.remove(observer);
    }

    public void addPriceObserver(PriceUpdate observer) {
        priceObservers.add(observer);
    }

    public void removePriceObserver(PriceUpdate observer) {
        priceObservers.remove(observer);
    }

    public void updateStock(String productName, int quantity) {
        stock.put(productName, quantity);
        for (StockUpdate observer : stockObservers) {
            observer.onStockUpdate(productName, quantity);
        }
    }

    public void updatePrice(String productName, double newPrice) {
        Double oldPrice = prices.get(productName);
        prices.put(productName, newPrice);
        for (PriceUpdate observer : priceObservers) {
            observer.onPriceUpdate(productName, newPrice, oldPrice != null ? oldPrice : newPrice);
        }
    }

    public int getStock(String productName) {
        return stock.getOrDefault(productName, 0);
    }

    public double getPrice(String productName) {
        return prices.getOrDefault(productName, 0.0);
    }
}