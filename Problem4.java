
class Vehicle {

    protected String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double calculateToll() {
        return 50.0;
    }
}

class Car extends Vehicle {

    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateToll() {
        return 70.0;
    }
}

class Truck extends Vehicle {

    private int axles;

    public Truck(String registrationNumber, int axles) {
        super(registrationNumber);
        this.axles = axles;
    }

    @Override
    public double calculateToll() {
        return 100.0 + (50.0 * axles);
    }
}

public class Problem4 {

    public static void main(String[] args) {

        Vehicle car = new Car("CAR101");
        Vehicle truck = new Truck("TRUCK202", 4);

        System.out.println("Car Toll: " + car.calculateToll());
        System.out.println("Truck Toll: " + truck.calculateToll());
    }
}