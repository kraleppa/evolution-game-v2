package io.github.kraleppa;
import io.github.kraleppa.server.MyWebSocketServer;

public class Main {
    public static void main(String[] args) {
//        Simulation simulation = new Simulation();
//        simulation.simulate(50, 10);
        new MyWebSocketServer().start();
    }
}
