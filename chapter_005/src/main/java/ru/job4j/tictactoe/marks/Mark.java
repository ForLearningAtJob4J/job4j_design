package ru.job4j.tictactoe.marks;

public interface Mark<T> {
    void print(T screen);
    boolean isMark(String mark);
}
