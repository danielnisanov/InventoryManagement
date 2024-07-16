package Client;

import java.io.InputStream;
import java.io.OutputStream;

// Interface defining a strategy for client-side behavior
public interface IClientStrategy {
    // Method to implement the client strategy using the server's input and output streams
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
