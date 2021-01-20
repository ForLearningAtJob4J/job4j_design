package ru.job4j.lsp.parking.vehicle;

public class Truck extends AbstractNumberedVehicle {

    public Truck(String number, int vehicleSize) {
        super(number);
        this.vehicleSize = vehicleSize;
    }
}
