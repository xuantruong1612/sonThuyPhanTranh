package network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import stpt.Client;
import stpt.Server;

public class MainServer {

    private ServerSocket serverSocket;
    private AtomicInteger clientIdCounter = new AtomicInteger(0);

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            Server.start = true; // Server đã sẵn sàng

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int clientId = clientIdCounter.incrementAndGet();
                System.out.println("Client #" + clientId + " connected.");

                Session_ME session = new Session_ME(clientSocket, clientId);
                Client.addClient(session); // add vào client list
                session.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            if (serverSocket != null) serverSocket.close();
            Server.start = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainServer server = new MainServer();
        server.start(14445);
    }
}
