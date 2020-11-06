package io.github.kraleppa.entities;

import io.github.kraleppa.util.Direction;
import io.github.kraleppa.util.Vector2D;

public class Animal extends Entity{
    private Direction direction;

    public Animal(Vector2D position, Direction direction) {
        super(position);
        this.direction = direction;
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
            map.moveEntity(this, newPosition);
            position = newPosition;
        }
    }


}
