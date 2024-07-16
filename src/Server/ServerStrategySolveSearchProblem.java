package Server;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    // Lock object for thread-safe operations
    private final Lock locker = new ReentrantLock(true);

    // Method to handle the server strategy
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(inFromClient);
             ObjectOutputStream out = new ObjectOutputStream(outToClient)) {

            // Read the maze object from the client
            Maze maze = (Maze) in.readObject();
            // Get the system temporary directory path
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            // Create a unique file path for the maze solution
            String mazePath = tempDirectoryPath + File.separator + maze.toString().hashCode() + ".solution";

            Solution solution;

            // Lock the operations for thread-safety
            locker.lock();
            try {
                File file = new File(mazePath);
                if (file.exists()) {
                    // If the solution file exists, read the solution from the file
                    try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                        solution = (Solution) fileIn.readObject();
                    }
                } else {
                    // If the solution file does not exist, solve the maze
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    Configurations instance = Configurations.getInstance();
                    ASearchingAlgorithm searcher = instance.getMazeSearchingAlgorithm();

                    // For testing purposes, you can use BestFirstSearch directly
                    // ASearchingAlgorithm searcher = new BestFirstSearch();

                    solution = searcher.solve(searchableMaze);

                    // Save the solution to the file
                    try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(file))) {
                        fileOut.writeObject(solution);
                    }
                }
            } finally {
                // Always unlock the locker in the finally block to ensure it is released
                locker.unlock();
            }

            // Send the solution back to the client
            out.writeObject(solution);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Private method to get the solution for a maze, either from the file or by solving it
    private Solution getSolution(Maze maze, String mazePath) throws IOException, ClassNotFoundException {
        Solution solution;

        // Lock the operations for thread-safety
        locker.lock();
        try {
            File file = new File(mazePath);
            if (file.exists()) {
                // If the solution file exists, read the solution from the file
                solution = readSolutionFromFile(mazePath);
            } else {
                locker.unlock();
                // If the solution file does not exist, solve the maze and save the solution
                solution = solveAndSaveSolution(maze, mazePath);
                locker.lock();
            }
        } finally {
            // Always unlock the locker in the finally block to ensure it is released
            locker.unlock();
        }

        return solution;
    }

    // Private method to read the solution from a file
    private Solution readSolutionFromFile(String mazePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(mazePath);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

            return (Solution) objIn.readObject();
        }
    }

    // Private method to solve the maze and save the solution to a file
    private Solution solveAndSaveSolution(Maze maze, String mazePath) throws IOException {
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Configurations instance = Configurations.getInstance();
        Solution solution = instance.getMazeSearchingAlgorithm().solve(searchableMaze);

        try (FileOutputStream fOut = new FileOutputStream(mazePath);
             ObjectOutputStream objOut = new ObjectOutputStream(fOut)) {

            objOut.writeObject(solution);
            objOut.flush();
        }

        return solution;
    }
}
