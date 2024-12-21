package com.vehicle.rental.agency;

public interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}
