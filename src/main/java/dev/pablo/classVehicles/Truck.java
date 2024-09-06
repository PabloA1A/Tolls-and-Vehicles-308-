package dev.pablo.classVehicles;

import dev.pablo.Vehicle;

public class Truck extends Vehicle {
    private int numberOfAxles;

    public Truck(String licensePlate, int numberOfAxles) {
        super(licensePlate);
        this.numberOfAxles = numberOfAxles;
    }

    @Override
    public double calculateToll() {
        return 50 * numberOfAxles;
    }

    @Override
    public String toString() {
        return super.toString() + " Axles: " + numberOfAxles;
    }
}
