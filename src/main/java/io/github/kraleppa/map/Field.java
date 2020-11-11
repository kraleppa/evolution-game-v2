package io.github.kraleppa.map;

import com.google.gson.annotations.Expose;
import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.util.Vector2D;

import java.util.*;

public class Field {
    @Expose
    private final Set<Animal> animals = new HashSet<>();
    @Expose
    private boolean isGrassed = false;
    @Expose
    public final Vector2D vector2D;
    @Expose
    public boolean isInJungle;

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
