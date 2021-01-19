package ru.job4j.lsp.parking.vehicle;

public class Truck extends AbstractNumberedVehicle {

    public Truck(String number) {
        super(number);
    }

    @Override
    public int getVehicleSize() {
        return 2;
    }
}
