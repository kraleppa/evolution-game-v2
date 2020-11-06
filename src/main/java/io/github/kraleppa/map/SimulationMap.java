package io.github.kraleppa.map;

import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.util.Vector2D;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final Map<Vector2D, Field> fields = new HashMap<>();
    public final Vector2D upperRight;

    public SimulationMap(Vector2D upperRight) {
        this.upperRight = upperRight;

        for (int x = 0; x < upperRight.x; x++){
            for (int y = 0; y < upperRight.y; y++){
                Vector2D vector2D = new Vector2D(x, y);
                fields.put(vector2D, new Field());
            }
        }
    }

    public Field getField(Vector2D position) {
        return fields.get(position);
    }

    public boolean isPositionLegal(Vector2D position){
        return position.precedes(upperRight) && position.follows(new Vector2D(-1, -1));
    }

    public void placeEntity(Entity entity){
        if (!isPositionLegal(entity.position)){
            throw new IllegalArgumentException("Wrong entity position!");
        }
        entity.setMap(this);
        getField(entity.position).addEntity(entity);
    }

    public void moveEntity(Entity entity, Vector2D to){
        if (!isPositionLegal(to)){
            throw new IllegalArgumentException("Wrong movement position!");
        }
        getField(entity.position).removeEntity(entity);
        getField(to).addEntity(entity);
    }


}
