package dev.pablo.classVehicles;

import dev.pablo.Vehicle;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double calculateToll() {
        return 50;
    }
}
