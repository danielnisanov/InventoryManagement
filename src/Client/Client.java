package Client;
import java.net.InetAddress;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private IClientStrategy strategy;
    private InetAddress serverIP;
    private int serverPort;

    public Client(InetAddress address, int serverport, IClientStrategy strategy) {
        this.strategy = strategy;
        this.serverIP = address;
        this.serverPort = serverport;
    }
    public void communicateWithServer() {
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            System.out.println("connected to server with IP:" + serverIP + ",Port:" + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
