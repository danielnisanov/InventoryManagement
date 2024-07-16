package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    // Singleton instance of the Configurations class
    private static Configurations instance;
    // Properties object to hold the configuration properties
    private Properties properties;

    // Private constructor to prevent instantiation from outside the class
    private Configurations() {
        properties = new Properties();
        // Load the properties from the config.properties file
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to get the singleton instance of the Configurations class
    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    // Method to get the thread pool size from the properties file, defaulting to 4 if not found
    public int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty("threadPoolSize", "4"));
    }

    // Method to get the maze generating algorithm specified in the properties file
    public AMazeGenerator getMazeGeneratingAlgorithm() {
        String algorithm = properties.getProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        switch (algorithm) {
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
            default:
                return new MyMazeGenerator();
        }
    }

    // Method to get the maze searching algorithm specified in the properties file
    public ASearchingAlgorithm getMazeSearchingAlgorithm() {
        String algorithm = properties.getProperty("mazeSearchingAlgorithm", "BestFirstSearch");
        switch (algorithm) {
            case "BreadthFirstSearch":
                return new BreadthFirstSearch();
            case "DepthFirstSearch":
                return new DepthFirstSearch();
            default:
                return new BestFirstSearch();
        }
    }
}
