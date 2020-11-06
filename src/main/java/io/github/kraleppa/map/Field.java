package io.github.kraleppa.map;

import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.util.Vector2D;

import java.util.HashSet;
import java.util.Set;

public class Field {
    private final Set<Entity> entities = new HashSet<>();

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public Set<Entity> getEntities() {
        return entities;
    }
}
