package com.vehicle.rental.agency;

public abstract class Vehicle {
    private final String vehicleId;
    private final String model;
    private final double baseRentalRate;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        if (vehicleId == null || model == null || baseRentalRate <= 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true; // Vehicles are available by default when created
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Abstract method for calculating rental cost based on the number of days
    public abstract double calculateRentalCost(int days);

    // This method checks if the vehicle is available for rental
    public boolean isAvailableForRental() {
        return isAvailable;
    }

    public void rent(Customer customer, int days) {
        if (!isAvailableForRental()) {
            throw new IllegalStateException("Vehicle is not available for rental.");
        }
        this.isAvailable = false; // Update vehicle availability to not available
    }

    @Override
    public String toString() {
        return String.format("Vehicle[ID=%s, Model=%s, Rate=%.2f, Available=%s]",
                vehicleId, model, baseRentalRate, isAvailable);
    }
}
