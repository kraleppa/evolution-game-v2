package io.github.kraleppa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.kraleppa.simulation.Simulation;

public class ConsoleMain {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.simulate(1);
        System.out.println(simulation.parseToJson());
    }
}
