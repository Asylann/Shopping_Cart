package com.ecommerce.builder;

public class ProductDirector {
    
    public Product createSmartphone() {
        return new Product.ProductBuilder()
                .withName("Smartphone")
                .withColor("Black")
                .withSize("6.1 inch")
                .withStorage(128)
                .withPrice(999.99)
                .build();
    }

    public Product createLaptop() {
        return new Product.ProductBuilder()
                .withName("Laptop")
                .withColor("Silver")
                .withSize("15.6 inch")
                .withStorage(512)
                .withPrice(1499.99)
                .build();
    }
}