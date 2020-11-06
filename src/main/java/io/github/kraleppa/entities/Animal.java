package io.github.kraleppa.entities;

import io.github.kraleppa.util.Vector2D;

public class Animal extends Entity{
    public Animal(Vector2D position) {
        super(position);
    }

    public Animal() {
        this(new Vector2D(0, 0));
    }

    @Override
    public String toString() {
        return "#";
    }
}
