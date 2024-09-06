package dev.pablo;

public abstract class Vehicle {
    protected String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public abstract double calculateToll();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (License Plate: " + licensePlate + ")";
    }
}
