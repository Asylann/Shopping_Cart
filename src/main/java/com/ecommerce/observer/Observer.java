package com.ecommerce.observer;

public interface Observer {
    void update(String productName, String updateType, String message);
}