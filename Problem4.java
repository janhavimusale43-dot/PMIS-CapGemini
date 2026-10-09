package Oops;

class Vehicle {

    // Field
    protected String registrationNumber;

    // Constructor
    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    // Method
    public double calculateToll() {
        return 50.0;
    }
}

// Car class
class Car extends Vehicle {

    // Constructor
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    // Method overriding
    @Override
    public double calculateToll() {
        return 70.0;
    }
}

// Truck class
class Truck extends Vehicle {

    // Field
    private int axles;

    // Constructor
    public Truck(String registrationNumber, int axles) {
        super(registrationNumber);
        this.axles = axles;
    }

    // Method overriding
    @Override
    public double calculateToll() {
        return 100.0 + (50.0 * axles);
    }
}

public class Problem4 {

    public static void main(String[] args) {

        // Parent references storing child objects
        Vehicle car = new Car("CAR101");
        Vehicle truck = new Truck("TRUCK202", 4);

        // Dynamic method dispatch
        System.out.println("Car Toll: " + car.calculateToll());
        System.out.println("Truck Toll: " + truck.calculateToll());
    }
}
