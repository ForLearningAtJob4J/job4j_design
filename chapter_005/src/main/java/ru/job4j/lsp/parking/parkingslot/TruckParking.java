package ru.job4j.lsp.parking.parkingslot;

public class TruckParking extends AbstractParkingSlot {
    public TruckParking(int slotSize, int vehicleSize) {
        super(false, false, slotSize, vehicleSize);
    }
}
