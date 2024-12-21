package com.vehicle.rental.agency;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String customerId;
    private final String name;
    private final List<RentalTransaction> rentalHistory;
    private final List<RentalTransaction> currentRentals;

    public Customer(String customerId, String name) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        this.customerId = customerId;
        this.name = name;
        this.rentalHistory = new ArrayList<>();
        this.currentRentals = new ArrayList<>();
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public List<RentalTransaction> getRentalHistory() {
        return new ArrayList<>(rentalHistory); // Return a copy for immutability
    }

    public List<RentalTransaction> getCurrentRentals() {
        return new ArrayList<>(currentRentals); // Return a copy for immutability
    }

    // Methods to manage rentals
    public void addRental(RentalTransaction rentalTransaction) {
        if (rentalTransaction == null) {
            throw new IllegalArgumentException("Rental transaction cannot be null");
        }
        currentRentals.add(rentalTransaction);
    }

    public void returnRental(String vehicleId) {
        RentalTransaction transactionToClose = null;

        for (RentalTransaction transaction : currentRentals) {
            if (transaction.getVehicle().getVehicleId().equals(vehicleId)) {
                transactionToClose = transaction;
                break;
            }
        }

        if (transactionToClose == null) {
            throw new IllegalArgumentException("No active rental found for the vehicle ID: " + vehicleId);
        }

        // Remove from current rentals and add to rental history
        currentRentals.remove(transactionToClose);
        rentalHistory.add(transactionToClose);
    }

    public boolean isEligibleForRental() {
        return currentRentals.size() < 2; // Example rule: A customer can rent up to 2 vehicles at a time
    }

    public void addTransaction(RentalTransaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
        currentRentals.add(transaction); // Add to current rentals
    }

    public void completeTransaction(RentalTransaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
        if (!currentRentals.remove(transaction)) {
            throw new IllegalStateException("Transaction not found in current rentals.");
        }
        rentalHistory.add(transaction); // Add to rental history
    }



    @Override
    public String toString() {
        return String.format("Customer[ID=%s, Name=%s, Active Rentals=%d, Total Rentals=%d]",
                customerId, name, currentRentals.size(), rentalHistory.size());
    }
}
