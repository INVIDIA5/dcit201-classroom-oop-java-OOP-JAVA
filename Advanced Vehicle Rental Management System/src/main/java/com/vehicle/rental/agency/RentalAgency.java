package com.vehicle.rental.agency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalAgency {
    private final List<Vehicle> fleet;       // List of vehicles in the fleet
    private final List<Customer> customers; // List of registered customers

    public RentalAgency(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Agency name cannot be null or empty");
        }
        this.fleet = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // Add a vehicle to the fleet
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        fleet.add(vehicle);
    }

    // List all available vehicles
    public List<Vehicle> getAvailableVehicles() {
        return fleet.stream()
                .filter(Vehicle::isAvailableForRental)
                .collect(Collectors.toList());
    }

    // Find an existing customer or create a new one
    public Customer findOrCreateCustomer(String customerId, String customerName) {
        Customer customer = findCustomer(customerId);
        if (customer == null) {
            customer = new Customer(customerId, customerName);
            customers.add(customer);
        }
        return customer;
    }

    // Find a customer by their ID
    public Customer findCustomer(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    // Rent a vehicle
    public void rentVehicle(String vehicleId, Customer customer, int rentalDays) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        Vehicle vehicle = fleet.stream()
                .filter(v -> v.getVehicleId().equals(vehicleId) && v.isAvailableForRental())
                .findFirst()
                .orElse(null);

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle with ID " + vehicleId + " is not available for rental");
        }

        double totalCost = vehicle.calculateRentalCost(rentalDays);
        vehicle.rent(customer, rentalDays);

        RentalTransaction transaction = new RentalTransaction(vehicle, customer, rentalDays, totalCost);
        customer.addTransaction(transaction);
    }

    // Return a vehicle
    public void returnVehicle(String vehicleId, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        RentalTransaction transaction = customer.getCurrentRentals().stream()
                .filter(t -> t.getVehicle().getVehicleId().equals(vehicleId))
                .findFirst()
                .orElse(null);

        if (transaction == null) {
            throw new IllegalArgumentException("No active rental found for vehicle ID " + vehicleId);
        }

        transaction.returnVehicle();
        customer.completeTransaction(transaction);
    }
}
