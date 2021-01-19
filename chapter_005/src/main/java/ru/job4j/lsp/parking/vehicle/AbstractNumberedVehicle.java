package ru.job4j.lsp.parking.vehicle;

import ru.job4j.lsp.parking.vehicle.Vehicle;

public abstract class AbstractNumberedVehicle implements Vehicle {
    private final String number;

    public AbstractNumberedVehicle(String number) {
        this.number = number;
    }

    @Override
    public int getVehicleSize() {
        throw new UnsupportedOperationException("YOU SHOULDN'T SEE ME");
    }

    @Override
    public String getNumber() {
        return number;
    }
}
