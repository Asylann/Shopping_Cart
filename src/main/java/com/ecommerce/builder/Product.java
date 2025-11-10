package com.ecommerce.builder;

public class Product {
    private String name;
    private String color;
    private String size;
    private int storage;
    private double price;

    // Private constructor to enforce use of builder
    private Product() {}

    public String getName() { return name; }
    public String getColor() { return color; }
    public String getSize() { return size; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }

    public static class ProductBuilder {
        private Product product = new Product();

        public ProductBuilder withName(String name) {
            product.name = name;
            return this;
        }

        public ProductBuilder withColor(String color) {
            product.color = color;
            return this;
        }

        public ProductBuilder withSize(String size) {
            product.size = size;
            return this;
        }

        public ProductBuilder withStorage(int storage) {
            product.storage = storage;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }
    }

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
}