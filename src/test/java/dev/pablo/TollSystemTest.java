package dev.pablo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.pablo.classVehicles.Car;
import dev.pablo.classVehicles.Motorcycle;
import dev.pablo.classVehicles.Truck;

@ExtendWith(MockitoExtension.class)
public class TollSystemTest {
    private TollStation tollStation;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private Vehicle mockVehicle;

    @BeforeEach
    void setUp() {
        tollStation = new TollStation("Test Station", "Test City");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void testTollStationInitialization() {
        assertThat(tollStation.getTotalCollected(), is(0.0));
    }

    @Test
    void testCalculateToll() {
        when(mockVehicle.calculateToll()).thenReturn(100.0);

        double toll = tollStation.calculateToll(mockVehicle);

        assertThat(toll, is(100.0));
        assertThat(tollStation.getTotalCollected(), is(100.0));
        verify(mockVehicle).calculateToll();
    }

    @Test
    void testCarToll() {
        Vehicle car = new Car("ABC123");
        assertThat(car.calculateToll(), is(100.0));
    }

    @Test
    void testMotorcycleToll() {
        Vehicle motorcycle = new Motorcycle("XYZ789");
        assertThat(motorcycle.calculateToll(), is(50.0));
    }

    @Test
    void testTruckToll() {
        Vehicle truck = new Truck("DEF456", 3);
        assertThat(truck.calculateToll(), is(150.0));
    }

    @Test
    void testMultipleVehicles() {
        tollStation.calculateToll(new Car("CAR001"));
        tollStation.calculateToll(new Motorcycle("MOTO001"));
        tollStation.calculateToll(new Truck("TRUCK001", 2));
        assertThat(tollStation.getTotalCollected(), is(250.0));
    }

    @Test
    void testPrintSummaryWithNoVehicles() {
        tollStation.printSummary();
        String output = outContent.toString();

        assertThat(output, containsString("Summary of Toll Station Test Station in Test City"));
        assertThat(output, containsString("Registered vehicles:"));
        assertThat(output, containsString("Total collected: $0.0"));
    }

    @Test
    void testPrintSummaryWithVehicles() {
        tollStation.calculateToll(new Car("CAR001"));
        tollStation.calculateToll(new Motorcycle("MOTO001"));
        tollStation.calculateToll(new Truck("TRUCK001", 2));

        tollStation.printSummary();
        String output = outContent.toString();

        assertThat(output, containsString("Summary of Toll Station Test Station in Test City"));
        assertThat(output, containsString("Registered vehicles:"));
        assertThat(output, containsString("- Car (License Plate: CAR001)"));
        assertThat(output, containsString("- Motorcycle (License Plate: MOTO001)"));
        assertThat(output, containsString("- Truck (License Plate: TRUCK001) Axles: 2"));
        assertThat(output, containsString("Total collected: $250.0"));
    }

    @Test
    void testMain() {
        TollSystem.main(new String[]{});

        String output = outContent.toString();

        assertThat(output, containsString("Summary of Toll Station North Station in Capital City"));
        assertThat(output, containsString("Registered vehicles:"));
        assertThat(output, containsString("- Car (License Plate: ABC123)"));
        assertThat(output, containsString("- Motorcycle (License Plate: XYZ789)"));
        assertThat(output, containsString("- Truck (License Plate: DEF456) Axles: 3"));
        assertThat(output, containsString("Total collected: $300.0"));
    }
}
