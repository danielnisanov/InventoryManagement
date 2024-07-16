package Server;

import java.io.*;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.nio.ByteBuffer;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {

        try (ObjectInputStream in = new ObjectInputStream(inFromClient);
             ObjectOutputStream out = new ObjectOutputStream(outToClient)) {

            int[] mazeDimensions = (int[]) in.readObject();

            if (mazeDimensions.length != 2) {
                throw new IOException("The array needs to contain two arguments!");
            }

            // Get the maze generator from the configuration
            Configurations instance = Configurations.getInstance();
            AMazeGenerator mazeGenerator = instance.getMazeGeneratingAlgorithm();
            // For testing purposes, you can use MyMazeGenerator directly
            // AMazeGenerator mazeGenerator = new MyMazeGenerator();

            Maze maze = mazeGenerator.generate(mazeDimensions[0], mazeDimensions[1]);

            // Compress the maze
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream);
            compressor.write(maze.toByteArray());
            compressor.flush();

            // Send the compressed maze to the client
            byte[] compressedMaze = byteArrayOutputStream.toByteArray();
            out.writeObject(compressedMaze);
            out.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
