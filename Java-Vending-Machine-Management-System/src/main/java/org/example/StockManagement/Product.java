package org.example.StockManagement;

import org.example.Exceptions.ProductExpiredException;
import org.example.ProductCategory;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private ProductCategory category;
    private LocalDate expiryDate;

    public Product(String name, double price, int quantity, ProductCategory category, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.expiryDate = expiryDate;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public ProductCategory getCategory() { return category; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) throws ProductExpiredException {
        if (isExpired()) {
            throw new ProductExpiredException(name + " is expired and cannot be sold.");
        }
        if (amount <= quantity) {
            quantity -= amount;
        } else {
            System.out.println("Not enough stock for " + name);
        }
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
