package ru.job4j.lsp.parking.parkingslot;

import ru.job4j.lsp.parking.vehicle.Vehicle;

public class CarParking extends AbstractParkingSlot {
    public CarParking(int slotSize) {
        super(true, false, slotSize, 1);
    }

    @Override
    public boolean in(Vehicle vehicle) {
        return false;
    }

    @Override
    public Vehicle out(String number) {
        return null;
    }
}
