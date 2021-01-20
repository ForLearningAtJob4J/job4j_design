package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.parkingslot.AbstractParkingSlot;
import ru.job4j.lsp.parking.parkingslot.CarParking;
import ru.job4j.lsp.parking.parkingslot.ParkingSlot;
import ru.job4j.lsp.parking.parkingslot.TruckParking;
import ru.job4j.lsp.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private final List<ParkingSlot> slots;

    public Parking(int carPlacesCount, int trackPlacesCount, int truckPlaceSize) {
        this.slots = new ArrayList<>(List.of(new CarParking(carPlacesCount), new TruckParking(trackPlacesCount, truckPlaceSize)));
    }

    public Parking(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public boolean in(Vehicle vehicle) {
        return false;
    }

    public Vehicle out(String number) {
        return null;
    }
}