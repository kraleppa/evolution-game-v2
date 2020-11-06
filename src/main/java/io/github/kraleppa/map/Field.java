package io.github.kraleppa.map;

import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.util.Vector2D;

import java.util.*;

public class Field {
    private final Set<Animal> animals = new HashSet<>();
    private boolean isGrassed = false;
    public final Vector2D vector2D;

    public Field(Vector2D vector2D) {
        this.vector2D = vector2D;
    }

    public void addAnimal(Animal animal){
        this.animals.add(animal);
    }

    public void removeAnimal(Animal animal){
        this.animals.remove(animal);
    }

    public Collection<Animal> getAnimals(){
        return animals;
    }

    public boolean isGrassed() {
        return isGrassed;
    }

    public void addGrass(){
        if (isGrassed){
            throw new IllegalStateException("Field is already grassed!");
        }
        isGrassed = true;
    }

    public void removeGrass(){
        if (!isGrassed){
            throw new IllegalStateException("Field is not currently grassed!");
        }
        isGrassed = false;
    }
}
