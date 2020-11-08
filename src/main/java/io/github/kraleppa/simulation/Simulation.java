package io.github.kraleppa.simulation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.kraleppa.animal.Animal;
import io.github.kraleppa.server.JSON;
import io.github.kraleppa.managers.EatingManager;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.managers.MovementManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

import org.java_websocket.WebSocket;
import java.util.Random;

public class Simulation {
    private final Vector2D upperRight = new Vector2D(10, 10);
    private final Vector2D jungleLowerLeft = new Vector2D(2, 2);
    private final Vector2D jungleUpperRight = new Vector2D(7, 7);
    private final GrowthManager growthManager = new GrowthManager(jungleLowerLeft, jungleUpperRight);
    private final WorldMap map = new WorldMap(upperRight, growthManager);
    private final MovementManager movementManager = new MovementManager(map, 1);
    private final EatingManager eatingManager = new EatingManager(map, 20, 20);
    private final Random random = new Random();
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private WebSocket socket;
    private int day = 0;

    private final ConsoleMapRenderer consoleMapRenderer = new ConsoleMapRenderer(map);

    public Simulation(WebSocket socket) {
        this.socket = socket;
    }

    public Simulation() {
    }

    private void simulateOneDay(){
        movementManager.performMovement();
        eatingManager.performEating();
        giveResult();
        growthManager.plantInSteppe();
        growthManager.plantInJungle();
        day++;
        giveResult();
    }

    public void simulate(int days, int animalsNumber) throws InterruptedException {
        prepareSimulation(animalsNumber);
        giveResult();
        Thread.sleep(100);
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

    private String parseToJson(){
        JSON json = new JSON(map.getFields(), day);
        return gson.toJson(json);
    }

    private void giveResult(){
        if (socket != null){
            socket.send(parseToJson());
        } else {
            System.out.println("Day: " + day);
            System.out.println(consoleMapRenderer.draw());
        }
    }
}
