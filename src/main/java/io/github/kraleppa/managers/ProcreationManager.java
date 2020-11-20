package io.github.kraleppa.managers;

import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.animal.Direction;
import io.github.kraleppa.animal.Genotype;
import io.github.kraleppa.map.Field;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;

public class ProcreationManager {
    private final WorldMap map;
    private final int procreationEnergyLimit;
    private final int startEnergy;

    public ProcreationManager(WorldMap map, int procreationEnergyLimit, int startEnergy) {
        this.map = map;
        this.procreationEnergyLimit = procreationEnergyLimit;
        this.startEnergy = startEnergy;
    }

    private Vector2D searchForFreePosition(Vector2D currentPosition){
        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++){
                Vector2D newPosition = currentPosition.add(new Vector2D(x, y));
                try {
                    if (map.getField(newPosition).getAnimals().size() == 0){
                        return newPosition;
                    }
                } catch (IllegalArgumentException ignored){ }
            }
        }
        return null;
    }

    private void procreate(Animal animal1, Animal animal2, Vector2D position){
        Genotype genotype = new Genotype(animal1.genotype, animal2.genotype);
        Animal animal = new Animal(position, Direction.N, genotype, startEnergy);
        map.placeAnimal(animal);
    }

    private void performProcreationOnField(Field field){
        Vector2D newPosition = searchForFreePosition(field.vector2D);
        if (newPosition == null){
            return;
        }
        if (field.getAnimals().size() < 2){
            return;
        }

        Animal animal1 = field.getAnimals().stream()
                .sorted()
                .findFirst().get();
        Animal animal2 = field.getAnimals().stream()
                .sorted()
                .filter(animal -> !animal.equals(animal1))
                .findFirst().get();

        if (animal1.energy < procreationEnergyLimit || animal2.energy < procreationEnergyLimit){
            return;
        }

        procreate(animal1, animal2, newPosition);
        animal1.energy -= procreationEnergyLimit;
        animal2.energy -= procreationEnergyLimit;
    }

    public void performProcreation(){
        map.getFields().stream()
                .filter(field -> field.getAnimals().size() >= 2)
                .forEach(this::performProcreationOnField);
    }
}
