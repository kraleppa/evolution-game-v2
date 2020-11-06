package io.github.kraleppa.util;

public enum Direction {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    public Vector2D toVector2D(){
        return switch (this) {
            case N -> new Vector2D(0, 1);
            case NE -> new Vector2D(1, 1);
            case E -> new Vector2D(1, 0);
            case SE -> new Vector2D(1, -1);
            case S -> new Vector2D(0, -1);
            case SW -> new Vector2D(-1, -1);
            case W -> new Vector2D(-1, 0);
            case NW -> new Vector2D(-1, 1);
        };
    }

    public Direction next() {
        return switch (this) {
            case N -> Direction.NE;
            case NE -> Direction.E;
            case E -> Direction.SE;
            case SE -> Direction.S;
            case S -> Direction.SW;
            case SW -> Direction.W;
            case W -> Direction.NW;
            case NW -> Direction.N;
        };
    }
}
