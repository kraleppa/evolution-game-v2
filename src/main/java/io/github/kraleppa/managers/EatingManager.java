package io.github.kraleppa.managers;

import io.github.kraleppa.map.Field;
import io.github.kraleppa.map.WorldMap;

public class EatingManager {
    private final WorldMap map;
    private final int plantEnergy;
    private final int maxEnergy;

    public EatingManager(WorldMap map, int plantEnergy, int maxEnergy) {
        this.map = map;
        this.plantEnergy = plantEnergy;
        this.maxEnergy = maxEnergy;
    }

    private void performEatingOnField(Field field){
        field.getAnimals().stream()
                .sorted()
                .findFirst()
                .ifPresent(animal -> {animal.eat(plantEnergy, maxEnergy); field.removeGrass();});
    }

    public void performEating(){
        map.getFields().stream()
                .filter(Field::isGrassed)
                .forEach(this::performEatingOnField);
    }
}
