package ru.job4j.dip.bad;

/**
 *  Абстракция хранилища есть, но сам класс зависит от конкретной реализации хранилища - MemStorage
 */

public class BadUsing {
    IStorage storage;

    public BadUsing() {
        storage = new MemStorage();
    }
}

class MemStorage implements IStorage {
}

interface IStorage {
}