package Server;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

public class Server {
    private int port;
    private IServerStrategy strategy;
    private int listenInMS;
    private boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listenInMS, IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
        this.listenInMS = listenInMS;
        this.stop = false;
        private ExecutorService threadPool;
    }

    public Server(int port, int listenInMS, IServerStrategy strategy) throws IOException {
        this.port = port;
        this.strategy = strategy;
        this.listenInMS = listenInMS;
        this.stop = false;
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            int sizeOfThreadPool = Integer.parseInt(prop.getProperty("threadPoolSize"));
            this.threadPool = Executors.newFixedThreadPool(sizeOfThreadPool);
        } catch (IOException ex) {
            this.threadPool = Executors.newFixedThreadPool(4); // Default size
        }
    }

    public void start() {
        new Thread(this::startThreadPool).start();
    }

    public void stop() {
        stop = true;
    }

    private void startThreadPool() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listenInMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(() -> { handleClient(clientSocket);});
                } catch (SocketTimeoutException e){
                    //checking for new connections
                }
            }
            threadPool.shutdownNow();
            serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}