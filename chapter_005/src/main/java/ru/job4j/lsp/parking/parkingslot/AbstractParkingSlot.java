package ru.job4j.lsp.parking.parkingslot;

public abstract class AbstractParkingSlot implements ParkingSlot {
    private final boolean canParkBigger;
    private final boolean canParkSmaller;
    private final int slotSize;
    private final int vehicleSize;

    public AbstractParkingSlot(boolean canParkBigger, boolean canParkSmaller, int slotSize, int vehicleSize) {
        this.canParkBigger = canParkBigger;
        this.canParkSmaller = canParkSmaller;
        this.slotSize = slotSize;
        this.vehicleSize = vehicleSize;
    }

    @Override
    public int getSlotSize() {
        return slotSize;
    }

    @Override
    public int getVehicleSize() {
        return vehicleSize;
    }

    @Override
    public boolean canParkBigger() {
        return canParkBigger;
    }

    @Override
    public boolean canParkSmaller() {
        return canParkSmaller;
    }
}