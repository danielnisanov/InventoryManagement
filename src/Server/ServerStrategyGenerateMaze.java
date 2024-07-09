package Server;

import java.io.InputStream;
import java.io.OutputStream;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outFromClient) {
        try {
            byte[] sizeArray = new byte[8];
            inFromClient.read(sizeArray);
            ByteBuffer buffer = ByteBuffer.wrap(sizeArray);
            int rows = buffer.getInt();
            int columns = buffer.getInt();

            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(rows, columns);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream);
            compressor.write(maze.toByteArray());

            outFromClient.write(byteArrayOutputStream.toByteArray());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
