package ru.job4j.tictactoe.players;

import ru.job4j.tictactoe.marks.Mark;

import java.io.OutputStream;

public class OutputStreamPlayer implements Player<OutputStream> {
    String name;
    Mark<OutputStream> mark;

    public OutputStreamPlayer(String name, Mark<OutputStream> mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String getName() {
        return name;
    }

    public Mark<OutputStream> getMark() {
        return mark;
    }
}
