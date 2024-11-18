package org.example.StockManagement;

import org.example.EndUsers.User;
import org.example.Exceptions.InsufficientBalanceException;
import org.example.Exceptions.ProductExpiredException;

public class VendingMachine {
    public Inventory inventory = new Inventory();
    private double earnings = 0;

    public void addProductToInventory(Product product) {
        inventory.addProduct(product);
    }

    public void updateStock(String name, int quantity) {
        inventory.updateStock(name, quantity);
    }

    public void removeProductFromInventory(String name) {
        inventory.removeProduct(name);
    }

    public void displayInventory() throws ProductExpiredException {
        inventory.displayProducts();
    }

    public void purchaseProduct(User user, String name, int quantity) {
        try {
            Product product = inventory.getProduct(name);
            if (product != null && product.getQuantity() >= quantity) {
                double totalCost = product.getPrice() * quantity;
                if (user.balance >= totalCost) {
                    earnings += totalCost;
                    user.balance -= totalCost;
                    product.reduceQuantity(quantity);
                    System.out.println("Purchased " + quantity + " of " + name + ". Total cost: $" + totalCost);
                } else {
                    throw new InsufficientBalanceException("Insufficient balance to complete the purchase.");
                }
            } else {
                System.out.println("Product not available or insufficient quantity.");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("Purchase error: " + e.getMessage());
        } catch (ProductExpiredException e) {
            System.out.println("Purchase error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during purchase: " + e.getMessage());
        }
    }

    public double getEarnings() {
        return earnings;
    }
}