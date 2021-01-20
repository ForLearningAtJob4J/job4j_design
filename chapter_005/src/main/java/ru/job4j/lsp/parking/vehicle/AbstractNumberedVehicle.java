package ru.job4j.lsp.parking.vehicle;

public abstract class AbstractNumberedVehicle implements Vehicle {
    protected int vehicleSize = 1;
    private final String number;

    public AbstractNumberedVehicle(String number) {
        this.number = number;
    }

    @Override
    public int getVehicleSize() {
        return vehicleSize;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
