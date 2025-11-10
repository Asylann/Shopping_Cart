package com.ecommerce.app;

import com.ecommerce.facade.ECommerceFacade;
import com.ecommerce.strategy.PercentageDiscount;
import com.ecommerce.builder.ProductDirector;
import com.ecommerce.factory.ProductFactory;
import com.ecommerce.observer.ProductInventory;
import com.ecommerce.cart.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        // Initialize components with proper dependency injection
        ProductDirector productDirector = new ProductDirector();
        ProductFactory productFactory = new ProductFactory();
        ProductInventory inventory = new ProductInventory();
        ShoppingCart cart = new ShoppingCart();

        // Create facade with injected dependencies
        ECommerceFacade system = new ECommerceFacade(
            productDirector,
            productFactory,
            inventory,
            cart
        );
        
        // Run the demo to showcase all patterns
        System.out.println("=== E-Commerce System Demo ===\n");
        system.demo();

        // Additional demonstration with discount
        System.out.println("\n=== Adding Discount Strategy ===");
        system.setDiscountStrategy(new PercentageDiscount(10));
        System.out.println("Total after 10% discount: $" + system.calculateTotal());
    }
}