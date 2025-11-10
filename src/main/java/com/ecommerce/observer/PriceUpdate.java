package com.ecommerce.observer;

public interface PriceUpdate {
    void onPriceUpdate(String productName, double newPrice, double oldPrice);
}