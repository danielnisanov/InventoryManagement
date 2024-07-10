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

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private final Lock locker = new ReentrantLock(true);

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {

        //option1
//          try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            MyDecompressorInputStream decompressor = new MyDecompressorInputStream(inFromClient);
//            decompressor.read(byteArrayOutputStream.toByteArray());
//
//            Maze maze = new Maze(byteArrayOutputStream.toByteArray());
//
//            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
//            String mazeFileName = tempDirectoryPath + "/" + maze.hashCode() + ".maze";
//            Path mazeFilePath = Paths.get(mazeFileName);
//
//            Solution solution;
//            if (Files.exists(mazeFilePath)) {
//                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(mazeFileName));
//                solution = (Solution) objectInputStream.readObject();
//                objectInputStream.close();
//            }
//            else {
//                ASearchingAlgorithm searcher = new BestFirstSearch();
//                solution = searcher.solve(new SearchableMaze(maze));
//
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(mazeFileName));
//                objectOutputStream.writeObject(solution);
//                objectOutputStream.flush();
//                objectOutputStream.close();
//            }
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outToClient);
//            objectOutputStream.writeObject(solution);
//            objectOutputStream.flush();
//        }
//        catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //option2
        //////////////////////////////////////////////////////////////////

//        try (ObjectInputStream in = new ObjectInputStream(inFromClient);
//             ObjectOutputStream out = new ObjectOutputStream(outToClient)) {
//
//            // Read maze from client
//            Maze maze = (Maze) in.readObject();
//
//            // Get temporary directory path
//            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
//            String mazePath = tempDirectoryPath + "maze" + maze.toString();
//
//            Solution solution = getSolution(maze, mazePath);
//
//            // Send solution back to client
//            out.writeObject(solution);
//            out.flush();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new IOException("Error in reading client data", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException("Error in solving maze or handling file", e);
//        }

        //////////////////////////////////////////////////
        try (ObjectInputStream in = new ObjectInputStream(inFromClient);
             ObjectOutputStream out = new ObjectOutputStream(outToClient)) {

            Maze maze = (Maze) in.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String mazePath = tempDirectoryPath + File.separator + maze.toString().hashCode() + ".solution";

            Solution solution;

            locker.lock();
            try {
                File file = new File(mazePath);
                if (file.exists()) {
                    // Read solution from file
                    try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                        solution = (Solution) fileIn.readObject();
                    }
                } else {
                    // Solve the maze
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    Configurations instance = Configurations.getInstance();
                    ASearchingAlgorithm searcher = instance.getMazeSearchingAlgorithm();
                    // For testing purposes, you can use BestFirstSearch directly
                    // ASearchingAlgorithm searcher = new BestFirstSearch();

                    solution = searcher.solve(searchableMaze);

                    // Save solution to file
                    try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(file))) {
                        fileOut.writeObject(solution);
                    }
                }
            } finally {
                locker.unlock();
            }

            // Send solution to client
            out.writeObject(solution);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private Solution getSolution(Maze maze, String mazePath) throws IOException, ClassNotFoundException {
            Solution solution;

            locker.lock();
            try {
                File file = new File(mazePath);
                if (file.exists()) {
                    solution = readSolutionFromFile(mazePath);
                } else {
                    locker.unlock();
                    solution = solveAndSaveSolution(maze, mazePath);
                    locker.lock();
                }
            } finally {
                locker.unlock();
            }

            return solution;
        }

        private Solution readSolutionFromFile(String mazePath) throws IOException, ClassNotFoundException {
            try (FileInputStream fileIn = new FileInputStream(mazePath);
                 ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

                return (Solution) objIn.readObject();
            }
        }

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