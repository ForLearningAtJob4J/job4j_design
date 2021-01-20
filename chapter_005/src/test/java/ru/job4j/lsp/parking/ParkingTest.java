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
    static final int TRUCKSIZE = 2;
    @Test
    public void whenParkingIs1Car0TrucksAnd1Car() {
        Parking parking = new Parking(1, 0, TRUCKSIZE);
        Vehicle car = new Car("CAR");
        assertTrue(parking.in(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingIs2Car0TrucksAnd2CarSameNumber() {
        Parking parking = new Parking(1, 0, TRUCKSIZE);
        Vehicle car = new Car("CAR");
        Vehicle carNotLegal = new Car("CAR");
        assertTrue(parking.in(car));
        assertFalse(parking.in(carNotLegal));
    }

    @Test
    public void whenParkingIs1Car0TrucksAnd1Truck() {
        Parking parking = new Parking(1, 0, TRUCKSIZE);
        Vehicle truck = new Truck("TRUCK", TRUCKSIZE);
        assertFalse(parking.in(truck));
    }

    @Test
    public void whenParkingIs1Car1TruckAnd1Car2Trucks() {
        Parking parking = new Parking(1, 1, TRUCKSIZE);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Truck("TRUCK", TRUCKSIZE);
        Vehicle truck2 = new Truck("ANOTHERTRUCK", TRUCKSIZE);
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertFalse(parking.in(truck2));
    }

    @Test
    public void whenParkingIsTRUCKSIZECars1TruckAnd2Trucks() {
        Parking parking = new Parking(TRUCKSIZE, 1, TRUCKSIZE);
        Vehicle truck1 = new Truck("TRUCK", TRUCKSIZE);
        Vehicle truck2 = new Truck("ANOTHERTRUCK", TRUCKSIZE);
        assertTrue(parking.in(truck1));
        assertTrue(parking.in(truck2));
    }

    @Test
    public void whenParkingIsTRUCKSIZECars1TruckAnd1Car2Trucks() {
        Parking parking = new Parking(TRUCKSIZE, 1, TRUCKSIZE);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Truck("TRUCK", TRUCKSIZE);
        Vehicle truck2 = new Truck("ANOTHERTRUCK", TRUCKSIZE);
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertFalse(parking.in(truck2));
    }

    @Test
    public void whenParkingNotDefaultIsTRUCKSIZECars0TrucksAnd1Car1Truck() {
        List<ParkingSlot> slots = new ArrayList<>(List.of(
                new CarParking(TRUCKSIZE)
        ));
        Parking parking = new Parking(slots);
        Vehicle car = new Car("CAR");
        assertTrue(parking.in(car));
        Vehicle truck = new Truck("TRUCK", TRUCKSIZE);
        assertFalse(parking.in(truck));
    }

    @Test
    public void whenParkingIsTRUCKSIZECar1TruckAnd1Car1TruckLater1CarOut1Truck() {
        Parking parking = new Parking(TRUCKSIZE, 1, TRUCKSIZE);
        Vehicle car = new Car("CAR");
        Vehicle truck1 = new Truck("TRUCK", TRUCKSIZE);
        assertTrue(parking.in(car));
        assertTrue(parking.in(truck1));
        assertEquals(car, parking.out("CAR"));
        Vehicle truck2 = new Truck("ANOTHERTRUCK", TRUCKSIZE);
        assertTrue(parking.in(truck2));
    }

    @Test
    public void whenParkingIsTRUCKSIZECar1TruckAnd0Cars2TrucksLater1TruckOut1Truck() {
        Parking parking = new Parking(TRUCKSIZE, 1, TRUCKSIZE);
        Vehicle truck1 = new Truck("TRUCK", TRUCKSIZE);
        Vehicle truck2 = new Truck("ANOTHERTRUCK", TRUCKSIZE);
        assertTrue(parking.in(truck1));
        assertTrue(parking.in(truck2));
        assertEquals(truck1, parking.out("TRUCK"));
        assertTrue(parking.in(truck1));
    }
}