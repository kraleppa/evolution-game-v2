package io.github.kraleppa.map;

import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.util.Vector2D;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final Map<Vector2D, Field> fields = new HashMap<>();
    public final Vector2D upperRight;
    private final GrowthManager growthManager;

    public WorldMap(Vector2D upperRight, GrowthManager growthManager) {
        this.upperRight = upperRight;
        this.growthManager = growthManager;

        for (int x = 0; x < upperRight.x; x++){
            for (int y = 0; y < upperRight.y; y++){
                Vector2D vector2D = new Vector2D(x, y);
                fields.put(vector2D, new Field());
            }
        }
    }

    public WorldMap(Vector2D upperRight) {
        this(upperRight, null);
    }

    public Field getField(Vector2D position) {
        return fields.get(position);
    }

    public Collection<Field> getFields() {
        return fields.values();
    }

    public boolean isPositionLegal(Vector2D position){
        return position.precedes(upperRight) && position.follows(new Vector2D(-1, -1));
    }

    public void placeAnimal(Animal animal){
        if (!isPositionLegal(animal.position)){
            throw new IllegalArgumentException("Wrong entity position!");
        }
        animal.bindMap(this);
        fields.get(animal.position).addAnimal(animal);
    }

    public void removeAnimal(Animal animal){
        if (!isPositionLegal(animal.position)){
            throw new IllegalArgumentException("Wrong entity position!");
        }
        fields.get(animal.position).removeAnimal(animal);
    }
}
