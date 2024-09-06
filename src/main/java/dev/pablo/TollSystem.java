package dev.pablo;

import dev.pablo.classVehicles.Car;
import dev.pablo.classVehicles.Motorcycle;
import dev.pablo.classVehicles.Truck;

public final class TollSystem {
   public static void main(String[] args) {
        TollStation station = new TollStation("North Station", "Capital City");

        Vehicle car1 = new Car("ABC123");
        Vehicle motorcycle1 = new Motorcycle("XYZ789");
        Vehicle truck1 = new Truck("DEF456", 3);

        station.calculateToll(car1);
        station.calculateToll(motorcycle1);
        station.calculateToll(truck1);

        station.printSummary();
    }
}
