package io.github.kraleppa.server;

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

    public MyWebSocketServer() {
        super(new InetSocketAddress(TCP_PORT));;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        sockets.add(webSocket);
        System.out.println("New connection from " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
        new Sender(webSocket, 100, 100).start();
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        sockets.remove(webSocket);
        System.out.println("Closed connection to " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Message from client: " + message);
        for (WebSocket sock : sockets) {
            sock.send(message);
        }
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
