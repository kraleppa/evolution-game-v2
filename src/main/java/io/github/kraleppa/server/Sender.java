package io.github.kraleppa.server;

import io.github.kraleppa.simulation.Simulation;
import lombok.SneakyThrows;
import org.java_websocket.WebSocket;

public class Sender extends Thread {
    private final WebSocket webSocket;
    private final Buffer buffer;

    public Sender(WebSocket webSocket, int days, int animalsNumber) {
        this.webSocket = webSocket;
        this.buffer = new Buffer();
        new Simulation(buffer, days, animalsNumber).start();
    }

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        Thread.sleep(200);
        while(true){
            String s = buffer.getFirst();
            if (s == null){
                break;
            }
            System.out.println(buffer);
            webSocket.send(s);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webSocket.close();
    }
}
