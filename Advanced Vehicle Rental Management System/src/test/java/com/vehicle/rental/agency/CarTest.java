package com.vehicle.rental.agency;

import org.junit.Test;

import static junit.framework.TestCase.*;


public class CarTest {

    @Test
    public void testCalculateRentalCost() {
        Car car = new Car("C123", "Sedan", 50.0);
        double cost = car.calculateRentalCost(3);
        assertEquals(165.0, cost);
    }

    @Test
    public void testRentAndReturn() {
        Car car = new Car("C123", "Sedan", 50.0);
        Customer customer = new Customer("C001", "Alice");

        car.rent(customer, 3);
        assertFalse(car.isAvailable());

        car.returnVehicle();
        assertTrue(car.isAvailable());
    }
}
