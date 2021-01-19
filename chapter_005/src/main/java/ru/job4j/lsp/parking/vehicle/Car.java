package ru.job4j.lsp.parking.vehicle;

public class Car extends AbstractNumberedVehicle {
    public Car(String number) {
        super(number);
    }

    @Override
    public int getVehicleSize() {
        return 1;
    }
}
