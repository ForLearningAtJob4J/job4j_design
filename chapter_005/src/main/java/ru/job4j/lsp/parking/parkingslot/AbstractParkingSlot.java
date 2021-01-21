package ru.job4j.lsp.parking.parkingslot;

import ru.job4j.lsp.parking.vehicle.Vehicle;

import java.util.Arrays;

public abstract class AbstractParkingSlot implements ParkingSlot {
    private final boolean canParkBigger;
    private final boolean canParkSmaller;
    private final int slotSize;
    private final int vehicleSize;
    private final Vehicle[] vehicles;

    public AbstractParkingSlot(boolean canParkBigger, boolean canParkSmaller, int slotSize, int vehicleSize) {
        this.canParkBigger = canParkBigger;
        this.canParkSmaller = canParkSmaller;
        this.slotSize = slotSize;
        this.vehicleSize = vehicleSize;
        vehicles = new Vehicle[slotSize * vehicleSize];
    }

    public boolean isIn(String number) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null
                    && vehicle.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
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

    @Override
    public boolean in(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Ghost-vehicles are not allowed! Call the police! :)");
        }

        if ("".equals(vehicle.getNumber())) {
            throw new IllegalArgumentException("Vehicles without number are not allowed! Call the police! :)");
        }

        if (slotSize * vehicleSize < vehicle.getVehicleSize()) {
            return false;
        }

        int startPosition = 0;
        int vSize = vehicle.getVehicleSize();
        boolean fit = false;
        if (vSize == vehicleSize
                || vehicleSize < vSize  && canParkBigger
                || vehicleSize > vSize && canParkSmaller) {
            loop: {
                for (int i = 0, vehiclesLength = vehicles.length; i < vehiclesLength; i += vSize) {
                    startPosition = i;
                    while (vehicles[i] == null) {
                        i += vSize;
                        if (i - startPosition == vSize) {
                            fit = true;
                            break loop;
                        }
                    }
                }
            }
        }
        if (fit) {
            Arrays.fill(vehicles, startPosition, startPosition + vSize, vehicle);
        }
        return fit;
    }

    @Override
    public Vehicle out(String number) {
        Vehicle ret = null;
        for (int i = 0, vehiclesLength = vehicles.length; i < vehiclesLength; i++) {
            Vehicle vehicle = vehicles[i];
            if (vehicle != null
                    && vehicle.getNumber().equals(number)) {
                ret = vehicle;
                Arrays.fill(vehicles, i, vehicle.getVehicleSize(), null);
            }
        }
        return ret;
    }
}