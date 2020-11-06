package io.github.kraleppa.simulation;

import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

public class Simulation {
    private final Vector2D upperRight = new Vector2D(10, 10);
    private final Vector2D jungleLowerLeft = new Vector2D(2, 2);
    private final Vector2D jungleUpperRight = new Vector2D(7, 7);
    private final GrowthManager growthManager = new GrowthManager(jungleLowerLeft, jungleUpperRight);
    private final WorldMap map = new WorldMap(upperRight, growthManager);
    private int day = 0;

    private final ConsoleMapRenderer consoleMapRenderer = new ConsoleMapRenderer(map);

    private void simulateOneDay(){
        growthManager.plantInSteppe();
        growthManager.plantInJungle();
        day++;
        System.out.println("Day: " + day);
        System.out.println(consoleMapRenderer.draw());
    }

    public void simulate(int days) throws InterruptedException {
        System.out.println("Day: 0");
        System.out.println(consoleMapRenderer.draw());
        for (int i = 0; i < days; i++){
            simulateOneDay();
            Thread.sleep(100);
        }
    }
}
