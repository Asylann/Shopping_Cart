package com.ecommerce.test;

import com.ecommerce.builder.Product;
import com.ecommerce.builder.ProductDirector;
import com.ecommerce.factory.ProductFactory;
import com.ecommerce.strategy.PercentageDiscount;
import com.ecommerce.observer.Customer;
import com.ecommerce.observer.Inventory;
import com.ecommerce.decorator.BaseCartItem;
import com.ecommerce.decorator.CartItem;
import com.ecommerce.decorator.GiftWrapDecorator;
import com.ecommerce.facade.ECommerceFacade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    void testBuilderPattern() {
        ProductDirector director = new ProductDirector();
        Product smartphone = director.createSmartphone();
        
        assertNotNull(smartphone);
        assertEquals("Smartphone", smartphone.getName());
        assertEquals("Black", smartphone.getColor());
    }

    @Test
    void testFactoryPattern() {
        ProductFactory factory = new ProductFactory();
        com.ecommerce.factory.Product electronics = factory.createProduct("electronics", "Laptop", 999.99);
        
        assertNotNull(electronics);
        assertEquals("Laptop", electronics.getName());
        assertEquals(999.99, electronics.getPrice());
    }

    @Test
    void testStrategyPattern() {
        PercentageDiscount discount = new PercentageDiscount(10);
        double discountedPrice = discount.applyDiscount(100.0);
        
        assertEquals(90.0, discountedPrice, 0.01);
    }

    @Test
    void testDecoratorPattern() {
        CartItem baseItem = new BaseCartItem("Laptop", 1000.0);
        CartItem giftWrappedItem = new GiftWrapDecorator(baseItem);
        
        assertEquals(1005.0, giftWrappedItem.getCost(), 0.01);
        assertTrue(giftWrappedItem.getDescription().contains("Gift Wrap"));
    }
}