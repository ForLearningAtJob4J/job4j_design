package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.parkingslot.AbstractParkingSlot;
import ru.job4j.lsp.parking.parkingslot.CarParking;
import ru.job4j.lsp.parking.parkingslot.ParkingSlot;
import ru.job4j.lsp.parking.parkingslot.TruckParking;
import ru.job4j.lsp.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private final List<ParkingSlot> slots;

    public Parking(int carPlacesCount, int trackPlacesCount, int truckPlaceSize) {
        this.slots = new ArrayList<>(List.of(new CarParking(carPlacesCount), new TruckParking(trackPlacesCount, truckPlaceSize)));
    }

    public Parking(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public boolean in(Vehicle vehicle) {
        if (slots.stream().anyMatch(parkingSlot -> parkingSlot.isIn(vehicle.getNumber()))) {
            throw new IllegalArgumentException("Car with this number is already at the parking! Call the police! :)");
        }

        return slots.stream()
                .filter(slot -> slot.getVehicleSize() == vehicle.getVehicleSize())
                .anyMatch(slots -> slots.in(vehicle))
                || slots.stream()
                .filter(slot -> slot.getVehicleSize() < vehicle.getVehicleSize() && slot.canParkBigger())
                .anyMatch(slots -> slots.in(vehicle))
                || slots.stream()
                .filter(slot -> slot.getVehicleSize() > vehicle.getVehicleSize() && slot.canParkSmaller())
                .anyMatch(slots -> slots.in(vehicle));
    }

    public Vehicle out(String number) {
        Vehicle ret = null;
        for (ParkingSlot slot : slots) {
            Vehicle out = slot.out(number);
            if (out != null) {
                ret = out;
                break;
            }
        }
        return ret;
    }
}