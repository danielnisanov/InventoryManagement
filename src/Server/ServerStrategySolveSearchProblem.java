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

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outFromClient) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MyDecompressorInputStream decompressor = new MyDecompressorInputStream(inFromClient);
            decompressor.read(byteArrayOutputStream.toByteArray());

            Maze maze = new Maze(byteArrayOutputStream.toByteArray());

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String mazeFileName = tempDirectoryPath + "/" + maze.hashCode() + ".maze";
            Path mazeFilePath = Paths.get(mazeFileName);

            Solution solution;
            if (Files.exists(mazeFilePath)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(mazeFileName));
                solution = (Solution) objectInputStream.readObject();
                objectInputStream.close();
            }
            else {
                ASearchingAlgorithm searcher = new BestFirstSearch();
                solution = searcher.solve(new SearchableMaze(maze));

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(mazeFileName));
                objectOutputStream.writeObject(solution);
                objectOutputStream.close();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outFromClient);
            objectOutputStream.writeObject(solution);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}