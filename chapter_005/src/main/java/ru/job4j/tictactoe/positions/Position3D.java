package ru.job4j.tictactoe.positions;

public class Position3D extends Position2D {
    private final int z;

    public Position3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public int getDimension(int dimensionNumber) {
        switch (dimensionNumber) {
            case 1:
                return x;
            case 2:
                return y;
            case 3:
                return z;
            default:
                throw new IllegalArgumentException("Position3D has only 3 dimensions");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Position3D that = (Position3D) o;

        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + z;
        return result;
    }

    @Override
    public int getDimensionsCount() {
        return 3;
    }
}
