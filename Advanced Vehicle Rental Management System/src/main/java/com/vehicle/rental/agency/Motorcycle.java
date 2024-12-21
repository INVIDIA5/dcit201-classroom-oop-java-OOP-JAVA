package com.vehicle.rental.agency;

import com.vehicle.rental.agency.exceptions.VehicleNotAvailableException;

public class Motorcycle extends Vehicle implements Rentable {
    private static final double HELMET_COST = 5.0;

    public Motorcycle(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }
        return (getBaseRentalRate() * days) + HELMET_COST;
    }

    @Override
    public boolean isAvailableForRental() {
        return !isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            throw new VehicleNotAvailableException("Motorcycle is not available for rental");
        }
        setAvailable(false);
        System.out.println("Motorcycle rented to customer: " + customer.getName());
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Motorcycle returned");
    }

    @Override
    public String toString() {
        return String.format("Motorcycle[ID=%s, Model=%s, Rate=%.2f, Available=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(), isAvailable());
    }


}
