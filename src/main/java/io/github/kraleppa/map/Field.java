package io.github.kraleppa.map;

import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.entities.Grass;
import io.github.kraleppa.util.Vector2D;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Field {
    private final Set<Animal> animals = new HashSet<>();
    private final Set<Grass> grasses = new HashSet<>();

    public void addEntity(Entity entity){
        if (entity instanceof Animal){
            animals.add((Animal) entity);
        } else if (entity instanceof Grass){
            grasses.add((Grass) entity);
        }
    }

    public void removeEntity(Entity entity){
        if (entity instanceof Animal){
            animals.remove(entity);
        } else if (entity instanceof Grass){
            grasses.remove(entity);
        }
    }

    public Set<Entity> getEntities() {
        return new HashSet<>() {{ addAll(animals); addAll(grasses); }};
    }
}
