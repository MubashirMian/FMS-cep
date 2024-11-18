package org.example.VendingManchine;

import org.example.EndUsers.Admin;
import org.example.EndUsers.User;
import org.example.ProductCategory;
import org.example.StockManagement.Product;
import org.example.StockManagement.VendingMachine;
import org.example.StockManagement.Inventory;

import java.time.LocalDate;
import java.util.Scanner;

public class VendingMachineSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineManager manager = new VendingMachineManager();
        manager.addMachine(vendingMachine);

        // Adding some dummy products to the vending machine's inventory
        Product product1 = new Product("Coke", 1.5, 10, ProductCategory.DRINK, LocalDate.of(2024, 12, 31));
        Product product2 = new Product("Chips", 1.0, 20, ProductCategory.SNACK, LocalDate.of(2024, 11, 30));
        Product product3 = new Product("Chocolate Bar", 1.2, 15, ProductCategory.CANDY, LocalDate.of(2025, 1, 15));
        Product product4 = new Product("Granola Bar", 2.0, 8, ProductCategory.HEALTHY, LocalDate.of(2025, 2, 28));

        vendingMachine.addProductToInventory(product1);
        vendingMachine.addProductToInventory(product2);
        vendingMachine.addProductToInventory(product3);
        vendingMachine.addProductToInventory(product4);

        System.out.println("Are you an Admin or Normal User? (Enter 'Admin' or 'User')");
        String userType = scanner.nextLine();

        User user;
        if (userType.equalsIgnoreCase("Admin")) {
            if (Admin.authenticate()) {
                user = new Admin("AdminUser");
            } else {
                return;
            }
        } else {
            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            user = new User("NormalUser", balance);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Products");
            System.out.println("2. Purchase Product");
            System.out.println("3. Check Balance");
            System.out.println("4. Recharge Balance");
            if (user instanceof Admin) {
                System.out.println("5. Add Product (Admin Only)");
                System.out.println("6. Update Product Stock (Admin Only)");
                System.out.println("7. Remove Product (Admin Only)");
                System.out.println("8. View Total Earnings (Admin Only)");
                System.out.println("9. Restock Inventory (Admin Only)");
            }
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    user.viewProducts(vendingMachine);
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    vendingMachine.purchaseProduct(user, productName, quantity);
                    break;
                case 3:
                    user.checkBalance();
                    break;
                case 4:
                    System.out.print("Enter recharge amount: ");
                    double rechargeAmount = scanner.nextDouble();
                    user.rechargeBalance(rechargeAmount);
                    break;
                case 5:
                    if (user instanceof Admin) {
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        System.out.print("Enter category (DRINK, SNACK, CANDY, HEALTHY): ");
                        ProductCategory category = ProductCategory.valueOf(scanner.next().toUpperCase());
                        System.out.print("Enter expiry date (yyyy-mm-dd): ");
                        LocalDate expiryDate = LocalDate.parse(scanner.next());
                        Product product = new Product(name, price, qty, category, expiryDate);
                        ((Admin) user).addProduct(vendingMachine, product);
                    } else {
                        System.out.println("You do not have permission to perform this operation.");
                    }
                    break;
                case 6:
                    if (user instanceof Admin) {
                        System.out.print("Enter product name: ");
                        String prodName = scanner.nextLine();
                        System.out.print("Enter new quantity: ");
                        int newQty = scanner.nextInt();
                        ((Admin) user).updateProductStock(vendingMachine, prodName, newQty);
                    } else {
                        System.out.println("You do not have permission to perform this operation.");
                    }
                    break;
                case 7:
                    if (user instanceof Admin) {
                        System.out.print("Enter product name to remove: ");
                        String nameToRemove = scanner.nextLine();
                        ((Admin) user).removeProduct(vendingMachine, nameToRemove);
                    } else {
                        System.out.println("You do not have permission to perform this operation.");
                    }
                    break;
                case 8:
                    if (user instanceof Admin) {
                        ((Admin) user).viewTotalEarnings(manager);
                    } else {
                        System.out.println("You do not have permission to perform this operation.");
                    }
                    break;
                case 9:
                    if (user instanceof Admin) {
                        vendingMachine.inventory.restockIfNeeded();
                    } else {
                        System.out.println("You do not have permission to perform this operation.");
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
