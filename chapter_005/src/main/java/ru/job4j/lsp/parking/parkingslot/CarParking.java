package ru.job4j.lsp.parking.parkingslot;

public class CarParking extends AbstractParkingSlot {
    public CarParking(int slotSize) {
        super(true, false, slotSize, 1);
    }
}
