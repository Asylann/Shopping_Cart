package com.ecommerce.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final Map<String, Integer> products = new HashMap<>();
    private final Map<String, Double> prices = new HashMap<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String productName, String updateType, String message) {
        for (Observer observer : observers) {
            observer.update(productName, updateType, message);
        }
    }

    public void updateStock(String productName, int quantity) {
        products.put(productName, quantity);
        if (quantity < 5) {
            notifyObservers(productName, "STOCK", "Low stock alert: " + quantity + " units remaining");
        }
    }

    public void updatePrice(String productName, double price) {
        Double oldPrice = prices.get(productName);
        prices.put(productName, price);
        if (oldPrice != null && price < oldPrice) {
            notifyObservers(productName, "PRICE", "Price dropped to: $" + price);
        }
    }
}