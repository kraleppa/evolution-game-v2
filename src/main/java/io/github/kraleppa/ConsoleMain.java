package io.github.kraleppa;
import io.github.kraleppa.simulation.Simulation;

public class ConsoleMain {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.simulate(1, 10);
        System.out.println(simulation.parseToJson());
    }
}
