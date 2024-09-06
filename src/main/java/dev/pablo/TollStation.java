package dev.pablo;

import java.util.ArrayList;
import java.util.List;

public class TollStation {
    private String name;
    private String city;
    private double totalCollected;
    private List<Vehicle> registeredVehicles;

    public TollStation(String name, String city) {
        this.name = name;
        this.city = city;
        this.totalCollected = 0;
        this.registeredVehicles = new ArrayList<>();
    }

    public double calculateToll(Vehicle vehicle) {
        double toll = vehicle.calculateToll();
        registerPayment(toll);
        registeredVehicles.add(vehicle);
        return toll;
    }

    private void registerPayment(double amount) {
        this.totalCollected += amount;
    }

    public double getTotalCollected() {
        return this.totalCollected;
    }

    public void printSummary() {
        System.out.println("Summary of Toll Station " + name + " in " + city);
        System.out.println("Registered vehicles:");
        for (Vehicle v : registeredVehicles) {
            System.out.println("- " + v);
        }
        System.out.println("Total collected: $" + totalCollected);
    }
}
