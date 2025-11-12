package com.ecommerce.observer;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<CustomerNotificationObserver> notificationObservers;

    public Customer(String name) {
        this.name = name;
        this.notificationObservers = new ArrayList<>();
    }

    public void addNotificationObserver(CustomerNotificationObserver observer) {
        notificationObservers.add(observer);
    }

    public void removeNotificationObserver(CustomerNotificationObserver observer) {
        notificationObservers.remove(observer);
    }

    public void notifyObservers(String productName, String message) {
        for (CustomerNotificationObserver observer : notificationObservers) {
            observer.notify(name, productName, message);
        }
    }

    public String getName() {
        return name;
    }
}