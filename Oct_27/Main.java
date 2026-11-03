package Oct_27;

import java.util.ArrayList;
import java.util.List;

// Interface
interface Rentable {
    double calculateRent(int days);

    boolean isAvailable();
}

// Abstract class
abstract class Vehicle implements Rentable {
    protected String id;
    protected String model;
    protected double dailyRate;
    protected boolean available;

    public Vehicle(String id, String model, double dailyRate) {
        this.id = id;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public abstract double calculateRent(int days);

    public String getVehicleName() {
        return getClass().getSimpleName() + " " + model + " (" + id + ")";
    }
}

// Car class
class Car extends Vehicle {

    public Car(String id, String model, double dailyRate) {
        super(id, model, dailyRate);
    }

    @Override
    public double calculateRent(int days) {
        return dailyRate * days;
    }
}

// Bike class
class Bike extends Vehicle {

    public Bike(String id, String model, double dailyRate) {
        super(id, model, dailyRate);
    }

    @Override
    public double calculateRent(int days) {
        double rent = dailyRate * days;

        if (days >= 7) {
            rent = rent * 0.90; // 10% discount
        }

        return rent;
    }
}

// Truck class
class Truck extends Vehicle {

    private static final double INSURANCE_CHARGE = 500.0;

    public Truck(String id, String model, double dailyRate) {
        super(id, model, dailyRate);
    }

    @Override
    public double calculateRent(int days) {
        return (dailyRate * days) + INSURANCE_CHARGE;
    }
}

// Customer class
class Customer {
    private String name;
    private List<Rental> rentalHistory;

    public Customer(String name) {
        this.name = name;
        this.rentalHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentalHistory.add(rental);
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }
}

// Rental class
class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double totalRent;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalRent = vehicle.calculateRent(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getTotalRent() {
        return totalRent;
    }
}

// RentalShop class
class RentalShop {
    private List<Vehicle> fleet;

    public RentalShop() {
        fleet = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public void rent(String vehicleId, Customer customer, int days)
            throws Exception {

        Vehicle selectedVehicle = null;

        // Find vehicle by ID
        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        // Check if vehicle exists
        if (selectedVehicle == null) {
            throw new Exception("Vehicle with ID " + vehicleId + " not found");
        }

        // Check availability
        if (!selectedVehicle.isAvailable()) {
            throw new Exception(
                selectedVehicle.getVehicleName() + " is already rented"
            );
        }

        // Validate rental duration
        if (days <= 0) {
            throw new Exception("Rental days must be greater than zero");
        }

        // Create rental
        Rental rental = new Rental(selectedVehicle, customer, days);

        // Update availability and customer history
        selectedVehicle.setAvailable(false);
        customer.addRental(rental);

        // Display rental details
        System.out.printf(
            "%s rented %s for %d days = %.2f%n",
            customer.getName(),
            selectedVehicle.getVehicleName(),
            days,
            rental.getTotalRent()
        );
    }

    public void giveBack(String vehicleId) throws Exception {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                if (vehicle.isAvailable()) {
                    throw new Exception(
                        vehicle.getVehicleName() + " is not currently rented"
                    );
                }

                vehicle.setAvailable(true);
                return;
            }
        }

        throw new Exception("Vehicle with ID " + vehicleId + " not found");
    }

    public void showAvailable() {
        System.out.println("Available vehicles:");

        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                System.out.println("  " + vehicle.getVehicleName());
            }
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        RentalShop shop = new RentalShop();

        // Create vehicles
        Car car = new Car("C1", "Swift", 2000);
        Bike bike = new Bike("B1", "Activa", 400);
        Truck truck = new Truck("T1", "Tata Ace", 2500);

        // Add vehicles to fleet
        shop.addVehicle(car);
        shop.addVehicle(bike);
        shop.addVehicle(truck);

        // Create customers
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");

        try {
            // Asha rents the bike for 8 days
            shop.rent("B1", asha, 8);

            // Ravi rents the truck for 2 days
            shop.rent("T1", ravi, 2);

            // Try to rent the same bike again
            shop.rent("B1", ravi, 3);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Return the truck
        try {
            shop.giveBack("T1");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display available vehicles
        shop.showAvailable();
    }
}