package io.github.kraleppa.animal;

import com.google.gson.annotations.Expose;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;

import java.util.Random;

public class Animal implements Comparable<Animal>{
    @Expose
    public Vector2D position;
    @Expose
    public Direction direction;
    public final Genotype genotype;
    @Expose
    public int energy;
    private WorldMap map;

    public Animal(Vector2D position, Direction direction, Genotype genotype, int energy){
        this.position = position;
        this.direction = direction;
        this.genotype = genotype;
        this.energy = energy;
    }

    public Animal(Vector2D position, Direction direction, int energy){
        this(position, direction, new Genotype(), energy);
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

        Vector2D newPosition = map.convertPosition(position.add(direction.toVector2D()));

        map.removeAnimal(this);
        position = newPosition;
        map.placeAnimal(this);
        energy -= energyCost;


        return true;
    }

    public void turnAround(int n){
        for (int i = 0; i < n; i++){
            direction = direction.next();
        }
    }

    public void turnAroundAuto(){
        //TODO
        turnAround(genotype.getRandomGene());
    }

    public void eat(int energy, int maximumEnergy){
        if (this.energy + energy > maximumEnergy){
            this.energy = maximumEnergy;
        } else {
            this.energy += energy;
        }
    }

    @Override
    public int compareTo(Animal other) {
        return Integer.compare(other.energy, this.energy);
    }

    public void bindMap(WorldMap map) {
        this.map = map;
    }
}
