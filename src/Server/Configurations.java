package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    private Properties properties;

    private Configurations() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty("threadPoolSize", "4"));
    }

    public AMazeGenerator getMazeGeneratingAlgorithm() {
        String Algorithm =  properties.getProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        if (Algorithm.compareTo("EmptyMazeGenerator")==0)
            return new EmptyMazeGenerator();
        else if (Algorithm.compareTo("SimpleMazeGenerator")==0)
            return new SimpleMazeGenerator();
        else
            return new MyMazeGenerator();
    }

    public ASearchingAlgorithm getMazeSearchingAlgorithm() {
        String Algorithm = properties.getProperty("mazeSearchingAlgorithm", "BestFirstSearch");
        if (Algorithm.compareTo("BreadthFirstSearch")==0)
            return new BreadthFirstSearch();
        else if (Algorithm.compareTo("DepthFirstSearch")==0)
            return new DepthFirstSearch();
        else
            return new BestFirstSearch();
    }
}