package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.simulation.Simulation;
import io.github.kraleppa.util.Vector2D;
import io.github.kraleppa.visualization.ConsoleMapRenderer;

public class ConsoleMain {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.simulate(20);


    }
}
