package ru.job4j.dip.bad;

import java.util.Map;

/**
 * Зависимость от конкретного вида хранилища - нужна абстракция
 */
public class BadStudent {
    Map<String, Double> progress;
    //бла бла бла
}
