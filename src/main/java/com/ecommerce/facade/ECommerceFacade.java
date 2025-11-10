package com.ecommerce.facade;

import com.ecommerce.builder.Product;
import com.ecommerce.builder.ProductDirector;
import com.ecommerce.factory.ProductFactory;
import com.ecommerce.strategy.DiscountStrategy;
import com.ecommerce.observer.Customer;
import com.ecommerce.observer.ProductInventory;
import com.ecommerce.decorator.BaseCartItem;
import com.ecommerce.decorator.CartItem;
import com.ecommerce.decorator.GiftWrapDecorator;
import com.ecommerce.decorator.WarrantyDecorator;
import com.ecommerce.cart.ShoppingCart;

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

    public void addProductToCart(String name, double price, boolean giftWrap, boolean warranty) {
        CartItem item = new BaseCartItem(name, price);
        if (giftWrap) {
            item = new GiftWrapDecorator(item);
        }
        if (warranty) {
            item = new WarrantyDecorator(item);
        }
        cart.addItem(item);
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        cart.setDiscountStrategy(strategy);
    }

    public void subscribeCustomer(Customer customer) {
        inventory.addStockObserver(customer);
        inventory.addPriceObserver(customer);
    }

    public double calculateTotal() {
        return cart.calculateTotal();
    }

    public void updateInventory(String productName, int quantity) {
        inventory.updateStock(productName, quantity);
    }

    public void updatePrice(String productName, double price) {
        inventory.updatePrice(productName, price);
    }

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