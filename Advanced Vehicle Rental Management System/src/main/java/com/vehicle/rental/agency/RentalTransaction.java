package com.vehicle.rental.agency;

import java.time.LocalDate;

public class RentalTransaction {
    private final Vehicle vehicle;
    private final Customer customer;
    private final int rentalDays;
    private final double totalCost;
    private final LocalDate rentalDate;
    private boolean returned;

    public RentalTransaction(Vehicle vehicle, Customer customer, int rentalDays, double totalCost) {
        if (vehicle == null || customer == null) {
            throw new IllegalArgumentException("Vehicle and Customer cannot be null.");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be positive.");
        }
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
        this.rentalDate = LocalDate.now();
        this.returned = false;
    }

    public void returnVehicle() {
        if (returned) {
            throw new IllegalStateException("Vehicle has already been returned.");
        }
        vehicle.setAvailable(true);
        this.returned = true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isReturned() {
        return returned;
    }

    @Override
    public String toString() {
        return String.format("RentalTransaction[VehicleID=%s, Customer=%s, Days=%d, TotalCost=%.2f, Date=%s]",
                vehicle.getVehicleId(), customer.getName(), rentalDays, totalCost, rentalDate);
    }
}
