package Server;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    // Port number on which the server listens for connections
    private int port;
    // Strategy pattern interface for server-side behavior
    private IServerStrategy strategy;
    // Timeout for server socket in milliseconds
    private int listenInMS;
    // Flag to control the server's running state
    private boolean stop;
    // Thread pool for handling multiple client connections
    private ExecutorService threadPool;

    // Constructor to initialize the server with the specified port, timeout, and strategy
    public Server(int port, int listenInMS, IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
        this.listenInMS = listenInMS;
        this.stop = false;

        // Initialize thread pool size from configurations
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            int sizeOfThreadPool = Integer.parseInt(prop.getProperty("threadPoolSize", "4")); // Default size is 4
            this.threadPool = Executors.newFixedThreadPool(sizeOfThreadPool);
        } catch (IOException ex) {
            ex.printStackTrace();
            // Fallback to default thread pool size if reading properties fails
            this.threadPool = Executors.newFixedThreadPool(4); // Default size
        }
    }

    // Method to start the server in a new thread
    public void start() {
        new Thread(this::startThreadPool).start();
    }

    // Method to stop the server
    public void stop() {
        stop = true;
    }

    // Private method to start the thread pool and listen for client connections
    private void startThreadPool() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listenInMS);
            while (!stop) {
                try {
                    // Accept a new client connection
                    Socket clientSocket = serverSocket.accept();
                    // Submit a task to handle the client connection
                    threadPool.submit(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    // Timeout occurred, check for new connections
                }
            }
            // Shut down the thread pool and close the server socket
            threadPool.shutdownNow();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private method to handle communication with a client
    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
