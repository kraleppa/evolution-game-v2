package io.github.kraleppa.simulation;

import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.managers.EatingManager;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.managers.MovementManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

import java.util.Random;

public class Simulation {
    private final Vector2D upperRight = new Vector2D(10, 10);
    private final Vector2D jungleLowerLeft = new Vector2D(2, 2);
    private final Vector2D jungleUpperRight = new Vector2D(7, 7);
    private final GrowthManager growthManager = new GrowthManager(jungleLowerLeft, jungleUpperRight);
    private final WorldMap map = new WorldMap(upperRight, growthManager);
    private final MovementManager movementManager = new MovementManager(map, 1);
    private final EatingManager eatingManager = new EatingManager(map, 1, 20);
    private final Random random = new Random();
    private int day = 0;

    private final ConsoleMapRenderer consoleMapRenderer = new ConsoleMapRenderer(map);

    private void simulateOneDay(){
        movementManager.performMovement();
        eatingManager.performEating();
        growthManager.plantInSteppe();
        growthManager.plantInJungle();
        day++;
        System.out.println("Day: " + day);
        System.out.println(consoleMapRenderer.draw());
    }

    public void simulate(int days) throws InterruptedException {
        prepareSimulation(1000);
        System.out.println("Day: 0");
        System.out.println(consoleMapRenderer.draw());
        for (int i = 0; i < days; i++){
            simulateOneDay();
            Thread.sleep(100);
        }
    }

    private void prepareSimulation(int animalsNumber){
        for (int i = 0; i < animalsNumber; i++){
            map.placeAnimal(new Animal(new Vector2D(random.nextInt(map.upperRight.x), random.nextInt(map.upperRight.y))));
        }
    }
}
