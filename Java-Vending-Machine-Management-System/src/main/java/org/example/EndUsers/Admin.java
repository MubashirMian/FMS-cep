package org.example.EndUsers;

import org.example.StockManagement.Product;
import org.example.StockManagement.VendingMachine;
import org.example.VendingManchine.VendingMachineManager;

import java.util.Scanner;

public class Admin extends User {
    private static final String ADMIN_PASSWORD = "admin";
    private static final int MAX_ATTEMPTS = 3;

    public Admin(String username) {
        super(username, 1_000_000.0);  // Set admin balance to a high value like $1,000,000
    }


    public static boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter Admin Password: ");
            String inputPassword = scanner.nextLine();
            if (inputPassword.equals(ADMIN_PASSWORD)) {
                return true;
            }
            attempts++;
            System.out.println("Incorrect password. Attempts left: " + (MAX_ATTEMPTS - attempts));
        }
        System.out.println("Too many failed attempts. Exiting application.");
        return false;
    }

    public void addProduct(VendingMachine vendingMachine, Product product) {
        vendingMachine.addProductToInventory(product);
        System.out.println("Product added: " + product.getName());
    }

    public void updateProductStock(VendingMachine vendingMachine, String productName, int newQuantity) {
        vendingMachine.updateStock(productName, newQuantity);
    }

    public void removeProduct(VendingMachine vendingMachine, String productName) {
        vendingMachine.removeProductFromInventory(productName);
    }

    public void viewTotalEarnings(VendingMachineManager manager) {
        System.out.println("Total Earnings: $" + manager.calculateTotalEarnings());
    }
}
