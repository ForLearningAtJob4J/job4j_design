package ru.job4j.isp;

public interface BadParking {
    void parkCar();
    void unparkCar();
    void getCapacity();
    double calculateFee(Car car);
    void doPayment(Car car);
}

class Car {

}
