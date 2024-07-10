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
        //option1
        /////////////////////////////////////////////
//        try {
//            byte[] sizeArray = new byte[8];
//            inFromClient.read(sizeArray);
//            ByteBuffer buffer = ByteBuffer.wrap(sizeArray);
//            int rows = buffer.getInt();
//            int columns = buffer.getInt();
//
//            AMazeGenerator mazeGenerator = new MyMazeGenerator();
//            Maze maze = mazeGenerator.generate(rows, columns);
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream);
//            compressor.write(maze.toByteArray());
//            compressor.flush();
//
//            byte[] compressedMaze = byteArrayOutputStream.toByteArray();
//            outToClient.write(compressedMaze);
//            outToClient.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        //option2
   ////////////////////////////////////////////////////////////////////
//        try (ObjectInputStream in = new ObjectInputStream(inFromClient);
//             ObjectOutputStream out = new ObjectOutputStream(outToClient);
//             ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
//             MyCompressorOutputStream compressorMaze = new MyCompressorOutputStream(byteOutput)) {
//
//            // Read maze dimensions from the client
//            int[] arrayFromClient = (int[]) in.readObject();
//            validateInput(arrayFromClient);
//
//            // Generate the maze using the configured maze generator
//            Maze maze = generateMaze(arrayFromClient);
//
//            // Compress and send the maze back to the client
//            compressorMaze.write(maze.toByteArray());
//            out.writeObject(byteOutput.toByteArray());
//            out.flush();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new IOException("Error in reading client data", e);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException("Error in generating or compressing maze", e);
//        }
//////////////////////////////////////////////////////////////////////////////

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



    //functions to option 2
//    private void validateInput(int[] arrayFromClient) throws IOException {
//        if (arrayFromClient.length != 2) {
//            throw new IOException("The array needs to contain two arguments!");
//        }
//    }
//
//    private Maze generateMaze(int[] dimensions) throws Exception {
//        Configurations instance = Configurations.getInstance();
//        AMazeGenerator mazeGenerator = instance.getMazeGeneratingAlgorithm();
//        return mazeGenerator.generate(dimensions[0], dimensions[1]);
//    }

}
