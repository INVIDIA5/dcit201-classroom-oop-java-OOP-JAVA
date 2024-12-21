package com.vehicle.rental.agency.exceptions;

public class InvalidRentalDaysException extends RuntimeException {
    public InvalidRentalDaysException(String message) {
        super(message);
    }
}
