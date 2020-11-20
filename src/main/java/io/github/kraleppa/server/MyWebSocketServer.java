package io.github.kraleppa.server;

import com.google.gson.Gson;
import io.github.kraleppa.simulation.Simulation;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class MyWebSocketServer extends WebSocketServer {
    private final static int TCP_PORT = 8081;
    private final Set<WebSocket> sockets = new HashSet<>();
    private final Gson gson = new Gson();

    public MyWebSocketServer() {
        super(new InetSocketAddress(TCP_PORT));;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        sockets.add(webSocket);
        System.out.println("New connection from " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        sockets.remove(webSocket);
        System.out.println("Closed connection to " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        Settings settings = gson.fromJson(message, Settings.class);
        new Sender(webSocket, settings).start();
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        if (webSocket != null) {
            sockets.remove(webSocket);
            System.out.println("ERROR from " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
            e.printStackTrace();
        }
    }
}
