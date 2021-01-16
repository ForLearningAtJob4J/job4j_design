package ru.job4j.lsp.parking;

public interface ParkingSlot {
    int getSlotSize();
    int getVehicleSize();
    boolean canParkBigger();
    boolean canParkSmaller();
    boolean in(Vehicle vehicle);
    Vehicle out(String number);
}
