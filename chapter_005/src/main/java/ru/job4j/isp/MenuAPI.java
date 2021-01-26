package ru.job4j.isp;

public interface MenuAPI {
    String add(String parentKey, String childName, Action action);

    boolean update(String key, String name, Action action);

    boolean remove(String key);

    Action getAction(String key);
}
