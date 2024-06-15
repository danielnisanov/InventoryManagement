package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     function that returns an instance of Maze
     */
    public Maze generate (int rows, int columns);

    /**
     function that returns the creation time of a maze
     */
    public long measureAlgorithmTimeMillis(int rows, int columns);
}