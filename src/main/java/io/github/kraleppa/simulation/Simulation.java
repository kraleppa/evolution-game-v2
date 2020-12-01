package io.github.kraleppa.simulation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.animal.Direction;
import io.github.kraleppa.managers.ProcreationManager;
import io.github.kraleppa.server.Buffer;
import io.github.kraleppa.server.JSON;
import io.github.kraleppa.managers.EatingManager;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.managers.MovementManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.server.Settings;
import io.github.kraleppa.util.Vector2D;

import java.util.Random;

public class Simulation extends Thread {
    private final Random random = new Random();
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private final GrowthManager growthManager;
    private final WorldMap map;
    private final MovementManager movementManager;
    private final EatingManager eatingManager;
    private final ProcreationManager procreationManager;

    private final Buffer buffer;
    private int day = 0;

    private final int days;


    public Simulation(Buffer buffer, Settings settings) {
        int movementCost = settings.movementCost;
        int maxEnergy = settings.maxEnergy;
        int plantEnergy = settings.plantEnergy;       //40
        this.growthManager = new GrowthManager(settings.jungleLowerLeft, settings.jungleUpperRight);
        this.map = new WorldMap(settings.upperRight, growthManager);
        this.movementManager = new MovementManager(map, movementCost);
        this.eatingManager = new EatingManager(map, plantEnergy, maxEnergy);
        this.procreationManager = new ProcreationManager(map, maxEnergy / 4, maxEnergy/2);
        this.buffer = buffer;
        this.days = settings.days;

        for (int i = 0; i < settings.animalsNumber; i++){
            Vector2D position = new Vector2D(random.nextInt(map.upperRight.x), random.nextInt(map.upperRight.y));
            Animal animal = new Animal(position, Direction.N, maxEnergy/2);
            map.placeAnimal(animal);
            animal.turnAroundAuto();
        }
    }

    private void simulateOneDay(){
        movementManager.performMovement();
        eatingManager.performEating();
        buffer.add(parseToJson());
        procreationManager.performProcreation();
        growthManager.plantInSteppe();
        growthManager.plantInJungle();
        day++;
        buffer.add(parseToJson());
    }

    @Override
    public void run() {
        super.run();
        simulate();
    }

    public void simulate() {
        buffer.add(parseToJson());
        for (int i = 0; i < days; i++){
            simulateOneDay();
        }
    }


    private String parseToJson(){
        int animals = map.getFields().stream()
                .map(field -> field.getAnimals().size())
                .reduce(0, Integer::sum);
        JSON json = new JSON(map.getFields(), day, animals);
        return gson.toJson(json);
    }
}
