package org.example.VendingManchine;

import org.example.StockManagement.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineManager {
    private List<VendingMachine> machines = new ArrayList<>();

    public void addMachine(VendingMachine machine) {
        machines.add(machine);
    }

    public double calculateTotalEarnings() {
        return machines.stream().mapToDouble(VendingMachine::getEarnings).sum();
    }
}