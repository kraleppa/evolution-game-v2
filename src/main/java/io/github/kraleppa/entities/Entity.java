package io.github.kraleppa.entities;

import io.github.kraleppa.util.Vector2D;

public abstract class Entity {
    public final Vector2D position;

    public Entity(Vector2D position) {
        this.position = position;
    }
}
