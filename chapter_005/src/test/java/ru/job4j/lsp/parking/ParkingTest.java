package ru.job4j.lsp.parking;

import org.junit.Test;
import ru.job4j.lsp.parking.parkingslot.CarParking;
import ru.job4j.lsp.parking.parkingslot.ParkingSlot;
import ru.job4j.lsp.parking.vehicle.Car;
import ru.job4j.lsp.parking.vehicle.Truck;
import ru.job4j.lsp.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenParking1Car0TrucksAnd1Car() {
        Parking parking = new Parking(1, 0);
        Vehicle car = new Car("CAR");
        assertTrue(parking.in(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParking2Car0TrucksAnd2CarSameNumber() {
        Parking parking = new Parking(1, 0);
        Vehicle car = new Car("CAR");
        Vehicle carNotLegal = new Car("CAR");
        assertTrue(parking.in(car));
        assertFalse(parking.in(carNotLegal));
    }

    @Test
    public void whenParking1Car0TrucksAnd1Truck() {
        Parking parking = new Parking(1, 0);
        Vehicle truck = new Truck("TRUCK");
        assertFalse(parking.in(truck));
    }

    @Test
    public void whenParkingIs1Car1TruckAnd1Car2Trucks() {
        Parking parking = new Parking(1, 1);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Car("TRUCK");
        Vehicle truck2 = new Car("ANOTHERTRUCK");
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertFalse(parking.in(truck2));
    }

    @Test
    public void whenParkingIs2Cars1TruckAnd2Trucks() {
        Parking parking = new Parking(2, 1);
        Vehicle truck1 = new Truck("TRUCK");
        Vehicle truck2 = new Truck("ANOTHERTRUCK");
        assertTrue(parking.in(truck1));
        assertTrue(parking.in(truck2));
    }

    @Test
    public void whenParkingIs2Cars1TruckAnd1Car2Trucks() {
        Parking parking = new Parking(2, 1);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Truck("TRUCK");
        Vehicle truck2 = new Truck("ANOTHERTRUCK");
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertFalse(parking.in(truck2));
    }

    @Test
    public void whenParkingNotDefault2Cars0TrucksAnd1Car1Truck() {
        List<ParkingSlot> slots = new ArrayList<>(List.of(
                new CarParking(2)
        ));
        Parking parking = new Parking(slots);
        Vehicle car = new Car("CAR");
        assertTrue(parking.in(car));

        Vehicle truck = new Truck("TRUCK");
        assertFalse(parking.in(truck));
    }

    @Test
    public void whenParkingIs2Car1TruckAnd1Car1TruckLater1CarOut1TruckIn() {
        Parking parking = new Parking(2, 1);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Car("TRUCK");
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertEquals(car, parking.out("CAR"));
        Vehicle truck2 = new Car("ANOTHERTRUCK");
        assertTrue(parking.in(truck2));
    }
}