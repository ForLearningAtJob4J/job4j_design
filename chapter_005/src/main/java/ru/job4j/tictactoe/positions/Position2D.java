package ru.job4j.tictactoe.positions;

public class Position2D implements Position {
    protected final int x;
    protected final int y;

    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getDimension(int dimensionNumber) {
        switch (dimensionNumber) {
            case 1:
                return x;
            case 2:
                return y;
            default:
                throw new IllegalArgumentException("Position2D has only 2 dimensions");
        }
    }

    @Override
    public int getDimensionsCount() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position2D that = (Position2D) o;

        if (x != that.x) {
            return false;
        }
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
