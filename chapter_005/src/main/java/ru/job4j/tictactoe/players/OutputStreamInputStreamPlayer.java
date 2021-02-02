package ru.job4j.tictactoe.players;

import ru.job4j.tictactoe.marks.Mark;

import java.io.InputStream;
import java.io.OutputStream;

public class OutputStreamInputStreamPlayer implements Player<InputStream, OutputStream> {
    String name;
    Mark mark;

    public OutputStreamInputStreamPlayer(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }
}
