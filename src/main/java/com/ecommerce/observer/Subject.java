package com.ecommerce.observer;

/**
 * Subject interface for observable objects.
 * Follows Interface Segregation Principle - focused interface for subjects.
 * Follows Dependency Inversion Principle - depends on Observer abstraction.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String productName, String updateType, String message);
}