package com.ecommerce.builder;

/**
 * Concrete strategy for building a smartphone.
 */
public class SmartphoneConstructionStrategy implements ProductConstructionStrategy {
    @Override
    public Product construct() {
        return new Product.ProductBuilder()
                .withName("Smartphone")
                .withColor("Black")
                .withSize("6.1 inch")
                .withStorage(128)
                .withPrice(999.99)
                .build();
    }
}
