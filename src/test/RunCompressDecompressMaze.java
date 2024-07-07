package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(100, 100); //Generate new maze

        // Size of the maze in cells (houses)
        int mazeSizeInCells = maze.getRows() * maze.getColumns();
        System.out.println("Size of the maze in cells: " + mazeSizeInCells);

        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            byte[] mazeBytes = maze.toByteArray();
            out.write(maze.toByteArray());
            out.flush();
            out.close();

            // Size of the maze in its most economical byte array representation
            int mazeSizeInBytes = mazeBytes.length;
            System.out.println("Size of the maze in byte array representation: " + mazeSizeInBytes);

            // Size of the saved file in bytes
            File mazeFile = new File(mazeFileName);
            long fileSizeInBytes = mazeFile.length();
            System.out.println("Size of the saved file in bytes: " + fileSizeInBytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
        //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
        //maze should be equal to loadedMaze
    }
}