package io.github.kraleppa.simulation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.server.Buffer;
import io.github.kraleppa.server.JSON;
import io.github.kraleppa.managers.EatingManager;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.managers.MovementManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;

import java.util.Random;

public class Simulation extends Thread {
    private final Random random = new Random();
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private final GrowthManager growthManager;
    private final WorldMap map;
    private final MovementManager movementManager;
    private final EatingManager eatingManager;

    private final Buffer buffer;
    private int day = 0;

    private final int days;


    public Simulation(Buffer buffer,
                      int days,
                      int animalsNumber,
                      Vector2D upperRight,
                      Vector2D jungleLowerLeft,
                      Vector2D jungleUpperRight) {
        this.growthManager = new GrowthManager(jungleLowerLeft, jungleUpperRight);
        this.map = new WorldMap(upperRight, growthManager);
        this.movementManager = new MovementManager(map, 1);
        this.eatingManager = new EatingManager(map, 20, 20);
        this.buffer = buffer;
        this.days = days;

        for (int i = 0; i < animalsNumber; i++){
            map.placeAnimal(new Animal(new Vector2D(random.nextInt(map.upperRight.x), random.nextInt(map.upperRight.y))));
        }
    }

    public Simulation(Buffer buffer, int days, int animalsNumber){
        this(buffer, days, animalsNumber, new Vector2D(40, 40), new Vector2D(14, 14), new Vector2D(26, 26));
    }

    private void simulateOneDay(){
        movementManager.performMovement();
        eatingManager.performEating();
        buffer.add(parseToJson());
        growthManager.plantInSteppe();
        growthManager.plantInJungle();
        day++;
        buffer.add(parseToJson());
    }

    @Override
    public void run() {
        super.run();
        simulate(1000);
    }

    public void simulate(int animalsNumber) {
        prepareSimulation(animalsNumber);
        buffer.add(parseToJson());
        for (int i = 0; i < days; i++){
            simulateOneDay();
        }
    }

    private void prepareSimulation(int animalsNumber){
        for (int i = 0; i < animalsNumber; i++){
            map.placeAnimal(new Animal(new Vector2D(random.nextInt(map.upperRight.x), random.nextInt(map.upperRight.y))));
        }
    }

    private String parseToJson(){
        JSON json = new JSON(map.getFields(), day);
        return gson.toJson(json);
    }
}
