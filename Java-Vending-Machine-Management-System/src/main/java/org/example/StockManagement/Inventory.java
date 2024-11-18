package org.example.StockManagement;

import org.example.Exceptions.ProductExpiredException;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products = new HashMap<>();
    private static final int RESTOCK_THRESHOLD = 5;

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public void updateStock(String name, int quantity) {
        Product product = products.get(name);
        if (product != null) {
            product.setQuantity(quantity);
            System.out.println("Updated stock for " + name + " to " + quantity);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void removeProduct(String name) {
        if (products.containsKey(name)) {
            products.remove(name);
            System.out.println("Product " + name + " removed from inventory.");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void displayProducts() throws ProductExpiredException {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : products.values()) {
                if (!product.isExpired()) {
                    System.out.println("Name: " + product.getName() + ", Price: $" + product.getPrice() +
                            ", Quantity: " + product.getQuantity() + ", Category: " + product.getCategory() +
                            ", Expiry Date: " + product.getExpiryDate());
                } else {
                    System.out.println(product.getName() + " is expired and will not be displayed.");
                }
            }
        }
    }

    public void restockIfNeeded() {
        for (Product product : products.values()) {
            if (product.getQuantity() < RESTOCK_THRESHOLD) {
                int restockedQuantity = RESTOCK_THRESHOLD * 2;
                product.setQuantity(product.getQuantity() + restockedQuantity);
                System.out.println("Restocked " + product.getName() + " to quantity: " + product.getQuantity());
            }
        }
    }
}

