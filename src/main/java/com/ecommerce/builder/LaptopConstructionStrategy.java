package com.ecommerce.builder;

/**
 * Concrete strategy for building a laptop.
 */
public class LaptopConstructionStrategy implements ProductConstructionStrategy {
    @Override
    public Product construct() {
        return new Product.ProductBuilder()
                .withName("Laptop")
                .withColor("Silver")
                .withSize("15.6 inch")
                .withStorage(512)
                .withPrice(1499.99)
                .build();
    }
}
