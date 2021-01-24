package ru.job4j.isp;

public interface IMenu {
    MenuItem add(String childName, IAction action);

    void update(String name, IAction action);

    void remove(String key);

    MenuItem find(String key);

    IAction getAction(String key);
}
