package io.github.kraleppa.server;

import io.github.kraleppa.simulation.Simulation;
import lombok.SneakyThrows;
import org.java_websocket.WebSocket;

public class Sender extends Thread {
    private final WebSocket webSocket;
    private final Buffer buffer;
    private final int time;

    public Sender(WebSocket webSocket, Settings settings) {
        this.webSocket = webSocket;
        this.buffer = new Buffer();
        this.time = settings.time;
        new Simulation(buffer, settings).start();
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
            webSocket.send(s);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webSocket.close();
    }
}
