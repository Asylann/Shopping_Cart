package com.ecommerce.observer;

public interface StockUpdate {
    void onStockUpdate(String productName, int quantity);
}