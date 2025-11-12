package com.ecommerce.facade;

import com.ecommerce.builder.Product;
import com.ecommerce.builder.ProductDirector;
import com.ecommerce.factory.ProductFactory;
import com.ecommerce.strategy.DiscountStrategy;
import com.ecommerce.observer.Customer;
import com.ecommerce.observer.StockUpdateNotifier;
import com.ecommerce.observer.PriceUpdateNotifier;
import com.ecommerce.observer.ProductInventory;
import com.ecommerce.decorator.BaseCartItem;
import com.ecommerce.decorator.CartItem;
import com.ecommerce.decorator.GiftWrapDecorator;
import com.ecommerce.decorator.WarrantyDecorator;
import com.ecommerce.cart.ShoppingCart;

/**
 * Facade for the e-commerce system.
 * Follows Facade Pattern and Single Responsibility Principle - provides a unified interface.
 * Follows Dependency Inversion Principle - depends on abstractions.
 */
public class ECommerceFacade {
    private final ProductDirector productDirector;
    private final ProductFactory productFactory;
    private final ProductInventory inventory;
    private final ShoppingCart cart;

    public ECommerceFacade(
            ProductDirector productDirector,
            ProductFactory productFactory,
            ProductInventory inventory,
            ShoppingCart cart) {
        this.productDirector = productDirector;
        this.productFactory = productFactory;
        this.inventory = inventory;
        this.cart = cart;
    }

    /**
     * Add a product to cart with optional decorations.
     */
    public void addProductToCart(String name, double price, boolean giftWrap, boolean warranty) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        CartItem item = new BaseCartItem(name, price);
        if (giftWrap) {
            item = new GiftWrapDecorator(item);
        }
        if (warranty) {
            item = new WarrantyDecorator(item);
        }
        cart.addItem(item);
    }

    /**
     * Set discount strategy for the cart.
     */
    public void setDiscountStrategy(DiscountStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Discount strategy cannot be null");
        }
        cart.setDiscountStrategy(strategy);
    }

    /**
     * Subscribe a customer to inventory updates.
     */
    public void subscribeCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        inventory.addStockObserver(new StockUpdateNotifier(customer));
        inventory.addPriceObserver(new PriceUpdateNotifier(customer));
    }

    /**
     * Calculate cart total with applied discount.
     */
    public double calculateTotal() {
        return cart.calculateTotal();
    }

    /**
     * Update inventory stock for a product.
     */
    public void updateInventory(String productName, int quantity) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        inventory.updateStock(productName, quantity);
    }

    /**
     * Update price for a product.
     */
    public void updatePrice(String productName, double price) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        inventory.updatePrice(productName, price);
    }

    /**
     * Run a demonstration of all design patterns.
     */
    public void demo() {
        // Demo Builder Pattern
        Product smartphone = productDirector.createSmartphone();
        System.out.println("Builder Pattern Demo - Created: " + smartphone);

        // Demo Factory Pattern
        com.ecommerce.factory.Product laptop = productFactory.createProduct("electronics", "Gaming Laptop", 1299.99);
        System.out.println("Factory Pattern Demo - Created: " + laptop.getDescription());

        // Demo Observer Pattern
        Customer john = new Customer("John Doe");
        subscribeCustomer(john);
        updateInventory("Gaming Laptop", 3);
        updatePrice("Gaming Laptop", 1199.99);

        // Demo Decorator Pattern
        addProductToCart("Gaming Laptop", 1299.99, true, true);
        System.out.println("Decorator Pattern Demo - Cart Items: ");
        cart.getItems().forEach(item -> 
            System.out.println("- " + item.getDescription() + " ($" + item.getCost() + ")"));
        System.out.println("Total Cost: $" + calculateTotal());
    }
}