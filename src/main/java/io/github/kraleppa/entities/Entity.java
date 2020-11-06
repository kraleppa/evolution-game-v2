package io.github.kraleppa.entities;

import io.github.kraleppa.map.SimulationMap;
import io.github.kraleppa.util.Vector2D;

public abstract class Entity {
    public Vector2D position;
    protected SimulationMap map;

    public void setMap(SimulationMap map) {
        this.map = map;
    }

    public Entity(Vector2D position) {
        this.position = position;
    }
}
