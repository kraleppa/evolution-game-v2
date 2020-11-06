package io.github.kraleppa.entities;

import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Direction;
import io.github.kraleppa.util.Vector2D;

public class Animal implements Comparable<Animal>{
    public Vector2D position;
    public Direction direction;
    private final Genotype genotype;
    private int energy;
    private WorldMap map;

    public Animal(Vector2D position, Direction direction, Genotype genotype, int energy){
        this.position = position;
        this.direction = direction;
        this.genotype = genotype;
        this.energy = energy;
    }

    public Animal(Vector2D position, Direction direction){
        this(position, direction, new Genotype(), 10);
    }

    public Animal(Vector2D position){
        this(position, Direction.N);
    }

    public boolean move(int energyCost){
        if (map == null){
            throw new IllegalStateException("Cannot move animal which isn't on the map!");
        }
        if (energyCost > energy){
            return false;
        }

        Vector2D newPosition = position.add(direction.toVector2D());
        if (map.isPositionLegal(newPosition)){
            map.removeAnimal(this);
            position = newPosition;
            map.placeAnimal(this);
        }

        return true;
    }

    public void turnAround(int n){
        for (int i = 0; i < n; i++){
            direction = direction.next();
        }
    }

    public void turnAroundAuto(){
        //TODO
    }

    public void eat(int energy, int maximumEnergy){
        //TODO
    }

    @Override
    public int compareTo(Animal other) {
        return Integer.compare(this.energy, other.energy);
    }

    public void bindMap(WorldMap map) {
        this.map = map;
    }
}
