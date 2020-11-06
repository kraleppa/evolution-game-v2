package io.github.kraleppa.entities;

import io.github.kraleppa.util.Direction;
import io.github.kraleppa.util.Vector2D;

public class Animal extends Entity{
    public Direction direction;
    private int healthPoints;

    public Animal(Vector2D position, Direction direction, int healthPoints) {
        super(position);
        this.direction = direction;
        this.healthPoints = healthPoints;
    }

    public Animal(Vector2D position, Direction direction) {
        this(position, direction, 10);
    }

    public Animal(Vector2D position) {
        this(position, Direction.N);
    }

    public void move(){
        if (map == null){
            throw new IllegalStateException("Cannot move animal which isn't on the map!");
        }
        Vector2D newPosition = position.add(direction.toVector2D());
        if (map.isPositionLegal(newPosition)){
            map.removeEntity(this);
            position = newPosition;
            map.placeEntity(this);
        }
    }

    public void turnAround(int n){
        for (int i = 0; i < n; i++){
            direction = direction.next();
        }
    }


}
