package com.ecommerce.builder;

/**
 * Immutable Product class using the Builder pattern.
 * Ensures all required fields are set and maintains immutability after construction.
 */
public final class Product {
    private final String name;
    private final String color;
    private final String size;
    private final int storage;
    private final double price;

    // Private constructor to enforce use of builder
    private Product(String name, String color, String size, int storage, double price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.storage = storage;
        this.price = price;
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public String getSize() { return size; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", storage=" + storage +
                ", price=" + price +
                '}';
    }

    /**
     * Builder for constructing Product instances with validation.
     */
    public static class ProductBuilder {
        private String name;
        private String color;
        private String size;
        private int storage;
        private double price;

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public ProductBuilder withSize(String size) {
            this.size = size;
            return this;
        }

        public ProductBuilder withStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
            this.price = price;
            return this;
        }

        public Product build() {
            validateFields();
            return new Product(name, color, size, storage, price);
        }

        private void validateFields() {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Product name is required");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
        }
    }
}