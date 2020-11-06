package io.github.kraleppa.entities;

import io.github.kraleppa.util.Vector2D;

public class Grass extends Entity{
    public int size;

    public Grass(Vector2D position, int size) {
        super(position);
        this.size = size;
    }

    public Grass(Vector2D position) {
        this(position, 10);
    }
}
