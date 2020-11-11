package io.github.kraleppa;
import io.github.kraleppa.server.MyWebSocketServer;
import io.github.kraleppa.simulation.Simulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Simulation simulation = new Simulation();
//        simulation.simulate(500, 1000);
        new MyWebSocketServer().start();
    }
}
