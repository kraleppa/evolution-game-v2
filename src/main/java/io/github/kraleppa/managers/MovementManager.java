package io.github.kraleppa.managers;

import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.map.WorldMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovementManager {
    private final WorldMap map;
    private final int movementCost;
    private final Set<Animal> movedAnimals = new HashSet<>();

    public MovementManager(WorldMap map, int movementCost) {
        this.map = map;
        this.movementCost = movementCost;
    }

    private void moveAnimal(Animal animal){
        if (!animal.move(movementCost)){
            map.removeAnimal(animal);
        } else {
            animal.turnAroundAuto();
        }
        movedAnimals.add(animal);

    }

    public void performMovement(){
        List<Animal> animalList = map.getFields().stream()
                .flatMap(animals -> animals.getAnimals().stream())
                .collect(Collectors.toList());

        for (Animal animal : animalList){
            if (!movedAnimals.contains(animal)){
                moveAnimal(animal);
            }
        }

        movedAnimals.clear();
    }
}
