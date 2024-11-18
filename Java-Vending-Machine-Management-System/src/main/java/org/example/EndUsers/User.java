package org.example.EndUsers;

import org.example.StockManagement.VendingMachine;

public class User {
    protected String username;
    public double balance;

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public void viewProducts(VendingMachine vendingMachine) {
        try {
            vendingMachine.displayInventory();
        } catch (Exception e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("Your balance is: $" + balance);
    }

    public void rechargeBalance(double amount) {
        final double MAX_BALANCE_CAP = 1_000_000.0;

        System.out.println("Attempting to recharge balance by: $" + amount);

        if (amount <= 0) {
            System.out.println("Recharge amount must be positive.");
            return;
        }

        if (balance >= MAX_BALANCE_CAP) {
            System.out.println("Balance has reached the maximum cap of $" + MAX_BALANCE_CAP);
            return;
        }

        balance += amount;
        if (balance > MAX_BALANCE_CAP) {
            balance = MAX_BALANCE_CAP; // Cap balance if it exceeds the max allowed balance
        }

        System.out.println("Balance recharged successfully. New balance: $" + balance);
    }


    }




