package com.vehicle.rental.agency;

import com.vehicle.rental.agency.exceptions.VehicleNotAvailableException;

public class Car extends Vehicle implements Rentable {
    private static final double INSURANCE_COST = 15.0;

    public Car(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) throw new IllegalArgumentException("Days must be positive");
        return (getBaseRentalRate() * days) + INSURANCE_COST;
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailableForRental()) { // Checks if available for rental
            throw new VehicleNotAvailableException("Car is not available");
        }
        setAvailable(false);
        System.out.println("Car rented to customer: " + customer.getName());
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Car returned");
    }

    @Override
    public String toString() {
        return String.format("Car[ID=%s, Model=%s, Rate=%.2f, Available=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(), isAvailable());
    }
}
