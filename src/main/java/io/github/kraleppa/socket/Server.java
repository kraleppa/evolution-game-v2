package io.github.kraleppa.socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            new ServerSession(clientSocket).start();
        }
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server(8081);
        server.start();
    }
}