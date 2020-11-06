package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

public class ConsoleMain {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2), null);
        ConsoleMapRenderer consoleMapRenderer = new ConsoleMapRenderer(worldMap);

        Animal animal = new Animal(new Vector2D(1, 1));
        Animal animal1 = new Animal(new Vector2D(0, 0));

        worldMap.placeAnimal(animal);
        worldMap.placeAnimal(animal1);

        System.out.println(consoleMapRenderer.draw());
        animal1.move(0);
        animal1.turnAround(2);
        System.out.println(consoleMapRenderer.draw());
        animal1.move(0);
        System.out.println(consoleMapRenderer.draw());


    }
}
