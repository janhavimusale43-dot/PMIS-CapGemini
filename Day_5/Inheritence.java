package Oops;

class Vehicle {

    String brand;

    void startEngine() {
        System.out.println(brand + " engine started.");
    }
}

class Bike extends Vehicle {

    boolean hasCarrier;

    void kickstand() {
        System.out.println("Kickstand put down.");
    }
}

public class Inheritence {

    public static void main(String[] args) {

        Bike myBike = new Bike();

        myBike.brand = "Pleasure";

        myBike.startEngine();
        myBike.kickstand();
    }
}