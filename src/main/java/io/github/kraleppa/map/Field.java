package io.github.kraleppa.map;

import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.util.Vector2D;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode
public class Field {
    private final Vector2D position;

    @EqualsAndHashCode.Exclude
    private final Set<Entity> entities = new HashSet<>();

    public Field(Vector2D position) {
        this.position = position;
    }

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
