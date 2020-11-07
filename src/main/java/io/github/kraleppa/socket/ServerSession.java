package io.github.kraleppa.socket;

import com.google.gson.Gson;
import io.github.kraleppa.simulation.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSession extends Thread{
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final Simulation simulation;
    private final Gson gson = new Gson();

    public ServerSession(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        simulation = new Simulation(out);
    }

    @Override
    public void run() {
        super.run();
        try {
            String settings = in.readLine();
            Settings settingsObject = gson.fromJson(settings, Settings.class);

            System.out.println();
            simulation.simulate(settingsObject.days, settingsObject.animals);
            out.println("Finished");
            in.close();
            out.close();
            clientSocket.close();
            sleep(10);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
