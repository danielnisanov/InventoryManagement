package Client;

import java.net.InetAddress;
import java.io.IOException;
import java.net.Socket;

public class Client {
    // Strategy pattern interface for client behavior
    private IClientStrategy strategy;

    // Server IP address
    private InetAddress serverIP;

    // Server port number
    private int serverPort;

    // Constructor to initialize the client with server address, port, and strategy
    public Client(InetAddress address, int serverport, IClientStrategy strategy) {
        this.strategy = strategy;
        this.serverIP = address;
        this.serverPort = serverport;
    }

    // Method to handle communication with the server
    public void communicateWithServer() {
        try (Socket serverSocket = new Socket(serverIP, serverPort)) {
            System.out.println("Connected to server with IP: " + serverIP + ", Port: " + serverPort);
            // Execute the client strategy with the server's input and output streams
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
