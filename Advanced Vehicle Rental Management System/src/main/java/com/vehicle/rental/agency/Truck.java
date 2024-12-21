package com.vehicle.rental.agency;

import com.vehicle.rental.agency.exceptions.VehicleNotAvailableException;

public class Truck extends Vehicle implements Rentable {
    private static final double LOADING_CHARGE = 20.0;

    public Truck(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }
        return (getBaseRentalRate() * days) + LOADING_CHARGE;
    }

    @Override
    public boolean isAvailableForRental() {
        return !isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            throw new VehicleNotAvailableException("Truck is not available for rental");
        }
        setAvailable(false);
        System.out.println("Truck rented to customer: " + customer.getName());
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Truck returned");
    }

    @Override
    public String toString() {
        return String.format("Truck[ID=%s, Model=%s, Rate=%.2f, Available=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(), isAvailable());
    }
}
