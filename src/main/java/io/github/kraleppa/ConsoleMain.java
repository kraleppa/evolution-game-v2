package io.github.kraleppa;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

public class ConsoleMain {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2));
        ConsoleMapRenderer consoleMapRenderer = new ConsoleMapRenderer(worldMap);

    }
}
