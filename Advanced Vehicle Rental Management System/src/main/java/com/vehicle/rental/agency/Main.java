package com.vehicle.rental.agency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalAgency rentalAgency = new RentalAgency("MyRentalAgency");

        System.out.println("Welcome to the Advanced Vehicle Rental Management System!");
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add a Vehicle to Fleet");
            System.out.println("2. Display Available Vehicles");
            System.out.println("3. Rent a Vehicle");
            System.out.println("4. Return a Vehicle");
            System.out.println("5. View Customer Rental History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addVehicleToFleet(rentalAgency, scanner);
                    break;
                case 2:
                    displayAvailableVehicles(rentalAgency);
                    break;
                case 3:
                    rentVehicle(rentalAgency, scanner);
                    break;
                case 4:
                    returnVehicle(rentalAgency, scanner);
                    break;
                case 5:
                    viewCustomerRentalHistory(rentalAgency, scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Vehicle Rental Management System!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Functionality 1: Add a vehicle to the fleet
    private static void addVehicleToFleet(RentalAgency rentalAgency, Scanner scanner) {
        System.out.println("\nChoose Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");
        System.out.print("Enter your choice: ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Base Rental Rate: ");
        double rate = scanner.nextDouble();

        Vehicle vehicle = null;
        switch (type) {
            case 1:
                vehicle = new Car(vehicleId, model, rate);
                break;
            case 2:
                vehicle = new Motorcycle(vehicleId, model, rate);
                break;
            case 3:
                vehicle = new Truck(vehicleId, model, rate);
                break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        rentalAgency.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    // Functionality 2: Display available vehicles
    private static void displayAvailableVehicles(RentalAgency rentalAgency) {
        System.out.println("\nAvailable Vehicles:");
        for (Vehicle vehicle : rentalAgency.getAvailableVehicles()) {
            System.out.println(vehicle);
        }
    }

    // Functionality 3: Rent a vehicle
    private static void rentVehicle(RentalAgency rentalAgency, Scanner scanner) {
        System.out.print("\nEnter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        Customer customer = rentalAgency.findOrCreateCustomer(customerId, customerName);

        System.out.println("\nAvailable Vehicles:");
        for (Vehicle vehicle : rentalAgency.getAvailableVehicles()) {
            System.out.println(vehicle);
        }

        System.out.print("Enter Vehicle ID to Rent: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Rental Days: ");
        int rentalDays = scanner.nextInt();

        try {
            rentalAgency.rentVehicle(vehicleId, customer, rentalDays);
            System.out.println("Vehicle rented successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Functionality 4: Return a vehicle
    private static void returnVehicle(RentalAgency rentalAgency, Scanner scanner) {
        System.out.print("\nEnter Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = rentalAgency.findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\nCustomer's Current Rentals:");
        for (RentalTransaction transaction : customer.getCurrentRentals()) {
            System.out.println(transaction);
        }

        System.out.print("Enter Vehicle ID to Return: ");
        String vehicleId = scanner.nextLine();

        try {
            rentalAgency.returnVehicle(vehicleId, customer);
            System.out.println("Vehicle returned successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Functionality 5: View customer rental history
    private static void viewCustomerRentalHistory(RentalAgency rentalAgency, Scanner scanner) {
        System.out.print("\nEnter Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = rentalAgency.findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\nCustomer's Rental History:");
        for (RentalTransaction transaction : customer.getRentalHistory()) {
            System.out.println(transaction);
        }
    }
}
