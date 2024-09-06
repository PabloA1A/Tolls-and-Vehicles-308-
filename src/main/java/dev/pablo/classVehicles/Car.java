package dev.pablo.classVehicles;

import dev.pablo.Vehicle;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double calculateToll() {
        return 100;
    }
}
