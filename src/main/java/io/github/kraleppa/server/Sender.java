package io.github.kraleppa.server;

import io.github.kraleppa.simulation.Simulation;
import lombok.SneakyThrows;
import org.java_websocket.WebSocket;

public class Sender extends Thread {
    private final WebSocket webSocket;
    private final Buffer buffer;

    public Sender(WebSocket webSocket, Settings settings) {
        this.webSocket = webSocket;
        this.buffer = new Buffer();
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
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webSocket.close();
    }
}
