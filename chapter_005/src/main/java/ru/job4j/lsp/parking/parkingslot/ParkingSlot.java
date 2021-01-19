package ru.job4j.lsp.parking.parkingslot;

import ru.job4j.lsp.parking.vehicle.Vehicle;

public interface ParkingSlot {
    int getSlotSize();
    int getVehicleSize();
    boolean canParkBigger();
    boolean canParkSmaller();
    boolean in(Vehicle vehicle);
    Vehicle out(String number);
}
