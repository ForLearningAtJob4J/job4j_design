package ru.job4j.lsp.parking.parkingslot;

import ru.job4j.lsp.parking.vehicle.Vehicle;

public class TruckParking extends AbstractParkingSlot {
    public TruckParking(int slotSize) {
        super(false, false, slotSize, 2);
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
