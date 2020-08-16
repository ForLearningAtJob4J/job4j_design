package ru.job4j.io;

public class ArgZip {
    private final ArgsName args;

    public ArgZip(String[] args) {
        this.args = ArgsName.of(args);
    }

    public boolean valid() {
        return args.peek("d") != null
                && args.peek("e") != null
                && args.peek("o") != null;
    }

    public String directory() {
        return args.peek("d");
    }

    public String exclude() {
        return args.peek("e");
    }

    public String output() {
        return args.peek("o");
    }
}