package io.github.kraleppa.map;

import io.github.kraleppa.animal.Animal;
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
                Field field = new Field(vector2D);
                field.isInJungle = growthManager.isFieldInJungle(field);
                fields.put(vector2D, field);
            }
        }

        growthManager.addFields(fields.values());
    }

    public Field getField(Vector2D position) {
        if (!fields.containsKey(position)){
            throw new IllegalArgumentException("Field does not exist!");
        }
        return fields.get(position);
    }

    public Collection<Field> getFields() {
        return fields.values();
    }

    public Vector2D convertPosition(Vector2D position){
        int nx;
        int ny;

        if (position.x < 0){
            nx = upperRight.x - 1;
        }
        else if (position.x >= upperRight.x){
            nx = 0;
        } else {
            nx = position.x;
        }

        if (position.y < 0){
            ny = upperRight.y - 1;
        }
        else if (position.y >= upperRight.y){
            ny = 0;
        } else {
            ny = position.y;
        }

        return new Vector2D(nx, ny);
    }

    public void placeAnimal(Animal animal){
        animal.bindMap(this);
        Field field = fields.get(animal.position);
        field.addAnimal(animal);
        growthManager.removeFromEmpty(field);
    }

    public void removeAnimal(Animal animal){
        Field field = fields.get(animal.position);
        field.removeAnimal(animal);
        growthManager.addIfEmpty(field);
    }
}
